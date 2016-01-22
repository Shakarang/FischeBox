//
//  ViewController.h
//  Fische_m-Simulator
//
//  Created by Maxime JUNGER on 28/04/15.
//  Copyright (c) 2015 Maxime JUNGER. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>

@interface ViewController : UIViewController <AVSpeechSynthesizerDelegate, UITableViewDataSource, UITableViewDelegate, UIAlertViewDelegate>

@property (weak, nonatomic) IBOutlet UIButton *playPauseButton;
@property (strong, nonatomic) AVSpeechSynthesizer *synthesizer;
@property (weak, nonatomic) NSString *str;
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UISwitch *voiceSwitch;
@end

