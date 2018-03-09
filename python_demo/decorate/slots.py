#! /usr/bin/python3
# coding: utf-8

class Student(object):
    pass

def set_age(self, age):
    self.age = age

def set_score(self, score):
    self.score = score

if __name__ == '__main__':
    s = Student()

    # 动态增加属性
    s.name = 'Mark'
    print(s.name)

    # 动态添加方法
    from types import MethodType
    s2 = Student()
    s2.set_age = MethodType(set_age, s2)
    s2.set_age(25)
    print(s2.age)

    # 给class绑定方法

    Student.set_age = MethodType(set_age, s, Student)
    s.set_age(100)
    print(s.score)
