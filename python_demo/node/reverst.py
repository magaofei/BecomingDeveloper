
class Node(object):
    def __init__(self, item, next):
        self.item = item
        self.next = next

class ReverseList(object):
    def __init__(self, head):
        self.head = head

    def reverse(self):
        pre = None
        next = None

        while( self.head ):
            next = self.head.next
            self.head.next = pre
            pre = self.head
            self.head = next
        
        return pre





