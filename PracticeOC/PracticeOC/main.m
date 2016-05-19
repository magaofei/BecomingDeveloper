//
//  main.m
//  PracticeOC
//
//  Created by Mark MaMian on 16/5/19.
//  Copyright © 2016年 Mark MaMian. All rights reserved.
//

#import <Foundation/Foundation.h>
@interface hello:NSObject{
    //- (void)methodhello;   //可能需要改写到头文件中
}
@end
@implementation hello

- (void) methodhello {printf("Hello,World\n");};

@end

int main(int argc, const char * argv[]) {
    @autoreleasepool {
        // insert code here...
        id x=[[hello alloc]init];  //实例化了一个id类型的变量x
        //x.methodhello;
        [x methodhello];   //输出
    }
    
    
    
    return 0;
}


