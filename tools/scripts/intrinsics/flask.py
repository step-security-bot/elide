import functools


# region
class Flask:
  def __init__(self, name: str):
    self.server = Elide.http
    self.router = self.server.getRouter()
    self.config = self.server.getConfig()

    # set the server to start automatically
    self.config.setAutoStart(True)

  def route(self, path: str):
    def decorator(func):
      @functools.wraps(func)
      def wrapper(request, response, context):
        # use the return value of the decorated function
        content = func(request, response, context)
        response.send(200, content)

      # register this route handler for evey method
      self.router.handle(None, path, wrapper)

      # return wrapper 
      return wrapper

    return decorator

  def configure(self, **kwargs):
    if kwargs["port"]:
      self.config.setPort(kwargs["port"])

    if kwargs["onBind"]:
      self.config.onBind(kwargs["onBind"])

  def start(self):
    self.server.start()


# endregion
# -------------------------------------------------------------------------

# create a new app
app = Flask(__name__)


# register route handlers
@app.route("/hello")
def hello(request, response, context):
  return "Hello from 'Flask'! 😉"


# configure the server (not idiomatic for Flask)
def bind_listener():
  print("'Flask' server running! 😉")


app.configure(port=3000, onBind=bind_listener)
print("✨ Configuration finished ✨")
