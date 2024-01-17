import os
from llama_index import GPTVectorStoreIndex, SimpleDirectoryReader, StorageContext, load_index_from_storage
import openai
import json

os.environ['OPENAI_API_KEY'] = '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'
documents = SimpleDirectoryReader('file_path_to_knowledge_base').load_data()
print(documents)

index = GPTVectorStoreIndex.from_documents(documents)

# Save the index
index.storage_context.persist('file_path_to_knowledge_base')

# rebuild storage context
storage_context = StorageContext.from_defaults(persist_dir='file_path_to_knowledge_base')
# load index
index = load_index_from_storage(storage_context)


class ChatBot:
    def __init__(self, api_key, index):
        self.index = index
        openai.api_key = api_key
        self.chat_history = []

    def generate_response(self, user_input):
        prompt = "\n".join([f"{message['role']}: {message['content']}"
                            for message in self.chat_history[-5:]])
        prompt += f"\nUser: {user_input}"
        query_engine = index.as_query_engine()
        response = query_engine.query(user_input)

        message = {"role": "assistant", "content": response.response}
        self.chat_history.append({"role": "user", "content": user_input})
        self.chat_history.append(message)
        return message

    def load_chat_history(self, filename):
        try:
            with open(filename, 'r') as f:
                self.chat_history = json.load(f)
        except FileNotFoundError:
            pass

    def save_chat_history(self, filename):
        with open(filename, 'w') as f:
            json.dump(self.chat_history, f)
