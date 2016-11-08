//
//  loginViewController.m
//  基础控件实战单元作业
//
//  Created by Mark MaMian on 2016/10/28.
//  Copyright © 2016年 Mark MaMian. All rights reserved.
//

#import "loginViewController.h"
#import "MyBlogViewController.h"
@interface loginViewController ()
@property (weak, nonatomic) IBOutlet UIImageView *accountTextBgImageView;
@property (weak, nonatomic) IBOutlet UITextField *accountTextField;
@property (weak, nonatomic) IBOutlet UIImageView *passwordTextBgImageView;
@property (weak, nonatomic) IBOutlet UITextField *passwordTextField;
@property (weak, nonatomic) IBOutlet UIButton *loginButton;
@property (weak, nonatomic) IBOutlet UIImageView *tipImageView;
@property (weak, nonatomic) IBOutlet UILabel *tipLabel;
@property (weak, nonatomic) IBOutlet UIActivityIndicatorView *loadingIndicatorView;

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
    [_loginButton setBackgroundImage:buttonBgImage forState:UIControlStateDisabled];
    [_loginButton setTitle:@"" forState:UIControlStateDisabled];
    
    /**
     添加监听

     @param loginButtonPressed <#loginButtonPressed description#>
     @return <#return value description#>
     */
//    [_loginButton addTarget:self action:@selector(loginButtonPressed) forControlEvents:UIControlEventTouchUpInside];
    
    _tipLabel.hidden = YES;
    _tipImageView.hidden = YES;
    //默认隐藏
    [_loadingIndicatorView stopAnimating];
}

//当登陆按钮被按下
- (void)loginButtonPressed {
    NSLog(@"Login button pressed");
}

- (BOOL)cancelLogin{
    //用户名admin  密码test
    if ([_accountTextField.text isEqualToString:@"admin"] && [_passwordTextField.text isEqualToString:@"test"]) {
        //验证成功
        [self showBlogViewController];//跳转
        _tipLabel.hidden = YES;
        _tipImageView.hidden = YES;
        return YES;  //验证成功
    } else {
        NSString *errorTip = @"";
        if (_accountTextField.text.length == 0) {
            errorTip = @"请输入用户名";
        } else if (_passwordTextField.text.length == 0) {
            errorTip = @"请输入密码";
        } else {
            errorTip = @"用户名或密码输入有误";
        }
        _tipImageView.hidden = NO;
        _tipLabel.hidden = NO;
        [_tipLabel setText:errorTip];
        
    }
    return NO;
    
    
}
- (IBAction)loginButtonAction:(id)sender {
    //关闭键盘
    [self.view endEditing:YES];
    
    //显示
    [_loadingIndicatorView startAnimating];
    [_loginButton setEnabled:NO];
    [_loadingIndicatorView setAlpha:0.5f]; //0.5的透明度
    [UIView animateWithDuration:1.f animations:^{
        [_loadingIndicatorView setAlpha:1.f];  //1透明度
    } completion:^(BOOL finished) {//当动画完成
        [_loadingIndicatorView stopAnimating];  //结束动画
        [_loginButton setEnabled:YES];
        [self cancelLogin]; //当动画完成调用
    }];
    
    
    
    
}



- (BOOL)shouldPerformSegueWithIdentifier:(NSString *)identifier sender:(id)sender {
    if ([self cancelLogin]) {
        return YES;
    } else {
        [self loginButtonAction:nil];
        return NO;
    }
}

- (void)showBlogViewController {
    //获取storyboard的ViewController
    MyBlogViewController *viewController = [[UIStoryboard storyboardWithName:@"Main" bundle:nil] instantiateViewControllerWithIdentifier:@"blogViewController"];
    [self presentViewController:viewController animated:YES completion:nil];//跳转
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
