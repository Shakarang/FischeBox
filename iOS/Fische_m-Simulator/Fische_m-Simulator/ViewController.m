//
//  ViewController.m
//  Fische_m-Simulator
//
//  Created by Maxime JUNGER on 28/04/15.
//  Copyright (c) 2015 Maxime JUNGER. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ViewController.h"
#import <AudioToolbox/AudioToolbox.h>

@import AVFoundation;

@interface ViewController () <AVAudioPlayerDelegate>

@property (assign) SystemSoundID _soundVoice;

@end

@implementation ViewController

BOOL speechPaused = 0;

int		before = 0;

NSString *quotes[] = {
	@"Putain mais tu fais chier !",
	@"Casse-toi.",
	@"TG.",
	@"La ferme.",
	@"Putain mais casse-toi.",
	@"Mais putain mais casse-toi.",
	@"Ferme ta gueule.",
	@"Franchement fermez vos gueules.",
	@"Ça sert à rien.",
	@"Mais putain mais fermez vos gueules.",
	@"Mais putain mais ferme ta gueule.",
	@"Ta gueule.",
	@"Tu fais chier.",
	@"En même temps si t'es une merde...",
	@"Casse-toi putain, casse-toi.",
	@"Connard.",
	@"Putain mais t'es vraiment un connard.",
	@"PD."
};

NSString *speakSound[] = {
	@"ZOOM0009",
	@"ZOOM0010",
	@"ZOOM0011",
	@"ZOOM0012",
	@"ZOOM0013",
	@"ZOOM0014",
	@"ZOOM0015",
	@"ZOOM0016",
	@"ZOOM0017",
	@"ZOOM0018",
	@"ZOOM0019",
	@"ZOOM0020",
	@"ZOOM0021",
	@"ZOOM0022",
	@"ZOOM0023",
	@"ZOOM0024",
	@"ZOOM0025",
	@"ZOOM0026",
};

- (void)viewDidLoad
{
	[super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
	self.synthesizer = [[AVSpeechSynthesizer alloc] init];
	speechPaused = NO;
}

-(void)dealloc
{
	AudioServicesDisposeSystemSoundID(self._soundVoice);

}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
	return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
	NSInteger nb = [self getNumberElem];
	return 18;
	return nb;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
	UITableViewCell *cell = [self.tableView dequeueReusableCellWithIdentifier:@"default"];

	UILabel *name = (UILabel *)[cell viewWithTag:3];
	name.text = quotes[indexPath.row];
	
	return cell;
}

-(int)getNumberElem
{
	int i;
	
	for (i = 0; quotes[i]; i++);
	return i;
}

- (IBAction)playPauseButtonPressed:(UIButton *)sender {

	int actual;
	
	actual = arc4random_uniform(18);
	while (actual == before) {
		actual = arc4random_uniform(18);
	}
	before = actual;
	self.str = quotes[actual];
	if (speechPaused == NO) {
		[self.playPauseButton setTitle:@"RANDOM" forState:UIControlStateNormal];
		[self.synthesizer continueSpeaking];
		speechPaused = YES;
	} else {
		[self.playPauseButton setTitle:@"RANDOM" forState:UIControlStateNormal];
		speechPaused = NO;
		[self.synthesizer pauseSpeakingAtBoundary:AVSpeechBoundaryImmediate];
	}
	if (self.synthesizer.speaking == NO) {
		if (self.voiceSwitch.isOn == true)
		{
			NSString *soundPath = [[NSBundle mainBundle]
															pathForResource:speakSound[actual] ofType:@"WAV"];
			NSURL *soundURL = [NSURL fileURLWithPath:soundPath];
			AudioServicesCreateSystemSoundID((__bridge CFURLRef)soundURL, &__soundVoice);
			AudioServicesPlaySystemSound(self._soundVoice);
		}
		else
		{
			AVSpeechUtterance *utterance = [[AVSpeechUtterance alloc] initWithString:self.str];
			utterance.rate = 0.2;
			utterance.voice = [AVSpeechSynthesisVoice voiceWithLanguage:@"fr"];
			[self.synthesizer speakUtterance:utterance];
		}
	}
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	
	self.str = quotes[indexPath.row];
	if (self.synthesizer.speaking == NO)
	{
		if (self.voiceSwitch.isOn == true)
		{
			NSString *soundPath = [[NSBundle mainBundle]
															pathForResource:speakSound[indexPath.row] ofType:@"WAV"];
			NSURL *soundURL = [NSURL fileURLWithPath:soundPath];
			AudioServicesCreateSystemSoundID((__bridge CFURLRef)soundURL, &__soundVoice);
			AudioServicesPlaySystemSound(self._soundVoice);
		}
		else
		{
			AVSpeechUtterance *utterance = [[AVSpeechUtterance alloc] initWithString:self.str];
			utterance.rate = 0.2;
			utterance.voice = [AVSpeechSynthesisVoice voiceWithLanguage:@"fr-FR"];
			[self.synthesizer speakUtterance:utterance];
		}
	}
	
}

-(void)speechSynthesizer:(AVSpeechSynthesizer *)synthesizer didFinishSpeechUtterance:(AVSpeechUtterance *)utterance {
	[self.playPauseButton setTitle:@"RANDOM" forState:UIControlStateNormal];
	speechPaused = NO;
	NSLog(@"Playback finished");
}

- (void)didReceiveMemoryWarning
{
	[super didReceiveMemoryWarning];
	// Dispose of any resources that can be recreated.
}

@end
