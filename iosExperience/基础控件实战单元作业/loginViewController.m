//
//  loginViewController.m
//  基础控件实战单元作业
//
//  Created by Mark MaMian on 2016/10/28.
//  Copyright © 2016年 Mark MaMian. All rights reserved.
//

#import "loginViewController.h"

@interface loginViewController ()
@property (weak, nonatomic) IBOutlet UIImageView *accountTextBgImageView;
@property (weak, nonatomic) IBOutlet UITextField *accountTextField;
@property (weak, nonatomic) IBOutlet UIImageView *passwordTextBgImageView;
@property (weak, nonatomic) IBOutlet UITextField *passwordTextField;
@property (weak, nonatomic) IBOutlet UIButton *loginButton;
@property (weak, nonatomic) IBOutlet UIImageView *tipImageView;
@property (weak, nonatomic) IBOutlet UILabel *tipLabel;

@end

@implementation loginViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    //设置TextField背景图片
    UIImage *inputBgImage = [_accountTextBgImageView.image resizableImageWithCapInsets:UIEdgeInsetsMake(12, 22, 12, 22) resizingMode:UIImageResizingModeStretch];
    [_accountTextBgImageView setImage:inputBgImage];
    [_passwordTextBgImageView setImage:inputBgImage];
    
    UIImage *buttonBgImage = [[UIImage imageNamed:@"button-green"] resizableImageWithCapInsets:UIEdgeInsetsMake(10, 10, 10, 10) resizingMode:UIImageResizingModeStretch];
    [_loginButton setBackgroundImage:buttonBgImage forState:UIControlStateNormal];
    [_loginButton setBackgroundImage:buttonBgImage forState:UIControlStateHighlighted];
    
    [_loginButton addTarget:self action:@selector(loginButtonPressed) forControlEvents:UIControlEventTouchUpInside];
    
}

//当登陆按钮被按下
- (void)loginButtonPressed {
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
