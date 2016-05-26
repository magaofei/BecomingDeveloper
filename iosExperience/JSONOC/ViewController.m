//
//  ViewController.m
//  JSONOC
//
//  Created by Mark MaMian on 16/5/20.
//  Copyright © 2016年 Mark MaMian. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()
@property (weak, nonatomic) IBOutlet UIButton *PressJson;
@property (weak, nonatomic) IBOutlet UILabel *MessageLabel;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    
    NSString *s=@"你好";
    _MessageLabel.text=&s;
                            

    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
