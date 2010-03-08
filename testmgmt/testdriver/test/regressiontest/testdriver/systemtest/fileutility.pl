#
# Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies).
# All rights reserved.
# This component and the accompanying materials are made available
# under the terms of "Eclipse Public License v1.0"
# which accompanies this distribution, and is available
# at the URL "http://www.eclipse.org/legal/epl-v10.html".
#
# Initial Contributors:
# Nokia Corporation - initial contribution.
#
# Contributors:
#
# Description: 
#

my $RET = 0;
my $cmd_param1 = $ARGV[0];
my $cmd_param2 = $ARGV[1];
my $cmd_param3 =  $ARGV[2];

if( $cmd_param1 eq "-local" )
{
	if( $cmd_param2 eq "-dir" )
	{
		my $cmd_param3 = $ARGV[2];
		my $cmd_param4 = $ARGV[3];
		if( $cmd_param3 eq "-d" )
		{
			if( -d $cmd_param4 )
			{
				$RET = 1;
			}
			else
			{
				$RET = 0;
			}
		}
		elsif( $cmd_param3 eq "-empty" )
		{
			if( -d $cmd_param4 )
			{
				opendir(DIR, $cmd_param4)   ||   die   "Can't   open   directory   $dir_name";   
				@dots = readdir(DIR); 
				my $count = @dots;
				if( $count > 2 )
				{
					$RET = 1;
				}
				else
				{
					$RET = 0;
				}
				closedir   DIR;
			}
		}
	}
	elsif( $cmd_param2 eq "-file" )
	{
		my $cmd_param3 = $ARGV[2];
		my $cmd_param4 = $ARGV[3];
		if( $cmd_param3 eq "-single" )
		{
			if( -e $cmd_param4 )
			{
				$RET = 1;
			}
			else
			{
				$RET = 0;
			}
		}
		elsif( $cmd_param3 eq "-regexp" )
		{
			my $i = 0;
			my $cmd_param5 = $ARGV[4];
			opendir(DIR, $cmd_param4)   ||   die   "Can't   open   directory   $dir_name";   
			@dots = readdir(DIR); 
			foreach $item (@dots)
			{
				if( $item =~ /$cmd_param5/i )
				{
					$i = $i + 1;
				}
			}
			if( $i > 0 )
			{
				$RET = 1;
			}
			else
			{
				$RET = 0;
			}
			closedir   DIR;
		}
	}
}
print $RET;
1;


