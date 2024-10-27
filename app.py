import requests
from flask import Flask, request, jsonify
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

# OpenAI API key
OPENAI_API_KEY = 'OPENAI_API_KEY'

@app.route('/generate-recipe', methods=['POST'])
def generate_recipe():
    data = request.json  # Get JSON data from the request
    ingredients = data.get('ingredients', [])
    
    prompt = f"Generate a high-protein recipe using the following ingredients: {', '.join(ingredients)}."

    # Call OpenAI API
    response = requests.post(
        'https://api.openai.com/v1/completions',
        headers={
            'Authorization': f'Bearer {OPENAI_API_KEY}',
            'Content-Type': 'application/json'
        },
        json={
            'model': 'text-davinci-003',  # Or any other model you prefer
            'prompt': prompt,
            'max_tokens': 150,
            'n': 1,
            'stop': None,
            'temperature': 0.7
        }
    )

    response_json = response.json()
    generated_recipe = response_json['choices'][0]['text']

    return jsonify({"recipe": generated_recipe})
    
@app.route('/test', methods=['GET'])
def test_connection():
    return jsonify({"message": "Backend is connected!"})

if __name__ == '__main__':
    app.run(debug=True)
