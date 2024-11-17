from flask import Flask, request
from openai import OpenAI

app = Flask(__name__)

@app.route('/process', methods=['POST'])
def process():
   data = request.json
   # print(str(data))
   client = OpenAI()
   completion = client.chat.completions.create(
      model="gpt-4o",
      messages=[
          {"role": "system", "content": "You are a helpful assistant for a workout tracking project. Your job is to provide science-backed suggestions for a person's workout schedule given their goals, body metrics, and current workout plan. You will be prompted with a JSON-formatted string containing the following: \"schedule\", which contains a list of their workouts on each day of the week; \"sex\", which contains their biological sex; \"height\", which contains their height in inches; \"weight\", which contains their weight in pounds; and \"goal\", which contains a short description of what they want to achieve by working out. Your response should come in the form of a single JSON-formatted string containing a single key: \"workout_rec\". The value should be a list containing an updated schedule, which represents your workout recommendation given the user's current schedule, sex, height, weight, and goal."},
          {
              "role": "user",
              "content": str(data)
          }
      ]
   )
   # print(completion.choices[0].message.content.strip('`').strip("json"))
   # d = eval('{"data":[{"key":"sample key","value":1}]}')
   # print(type(d))
   return eval(completion.choices[0].message.content.strip('`').strip("json"))

if __name__ == '__main__':
   app.run(port=5000)