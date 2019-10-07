import numpy as np
import matplotlib as mpl
import matplotlib.pyplot as plt
import seaborn as sns
from flask import Flask, request

app = Flask(__name__)

@app.route("/")
def index():
    return "Welcome to the Plot Utility!\nUsage: POST Request with JSON data.\n"

@app.route("/plot", methods=["GET", "POST"])
def plot_data():
    args = request.get_json(force=True)
    data = args["data"]
    plot = sns.lineplot(data=np.array(data))
    fig = plot.get_figure()
    fig.savefig("plot.png")
    return args
    
if __name__ == "__main__":
    app.run(debug=True)
