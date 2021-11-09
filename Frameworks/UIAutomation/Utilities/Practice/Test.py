class Test:
    # this is constructor. Its called when an object is created.  Even assign  class variables
    def __init__(self, name) -> None:
        self.name=name
        print(name)
    def bark(self):
        print("bark" + self.name)

class Pet:
    def __init__(self, name):
        self.name = name
    def speak(self):
        print( self.name + " Bark")

class Cat(Pet):
    def __init__(self, name, owner):
        super().__init__(name)
        self.owner = owner
    def speak(self):
        print( self.name + " Barks " + self.owner)

class Dog:
    def __init__(self):
        pass



obj = Cat("Namy", "Harish")
obj.speak()
o2 = Pet("china")
o2.speak()
print(type(obj))

