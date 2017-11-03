# coding: utf-8
"""
threading for dict demo
运行多线程插入的字典中，不同的 key 的话没问题，如果是相同的 key 就不是线程安全了
"""

from threading import Thread


class Apple(object):

    def __init__(self, apples=None):
        self.apples = apples

    def generate(self):
        """
        生成
        """
        self.apples = {}
        for i in range(1000):
            self.apples[i] = i

    def loop(self, i):
        self.apples[i] = i + 1

    def threading(self):
        threads = []
        for i in range(1000):
            t = Thread(target=self.loop, args=(i,))
            threads.append(t)

        for i in threads:
            i.start()


if __name__ == '__main__':
    foo = Apple()
    foo.generate()
    print(foo.apples)
    foo.threading()
    print(foo.apples)
    pass
