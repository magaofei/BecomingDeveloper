import dis

class Student(object):
    def __init__(self, name, score):
        self.__name = name
        self.__score = score

    def print_score(self):
        print("%s %s" % (self.__name, self.__score))


if __name__ == '__main__':
    bart = Student('Bart Simpson', 98)
    # bart.__name

    print(dis.dis(Student))
    

    