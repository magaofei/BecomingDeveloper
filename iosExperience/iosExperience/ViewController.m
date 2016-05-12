//
//  ViewController.m
//  iosExperience
//
//  Created by Mark MaMian on 16/5/12.
//  Copyright © 2016年 Mark MaMian. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController
- (IBAction)userButtonClick:(id)sender {
    NSLog(@"点击成功");
    
    //定义一个对话框
           //弹出对话框
    UIAlertView *alert=[[UIAlertView alloc]initWithTitle:@"第一次" message:@"我也第一次" delegate:self cancelButtonTitle:@"取消" otherButtonTitles:@"确定", nil];
    [alert show];
    
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
