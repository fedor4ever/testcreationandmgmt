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

my $cmd_results = `jps -l`;
print $cmd_results;
@array = split(/\s/,$cmd_results);   
my $pid_td = 0;
my $count = 0;
while ($count < @array) 
{
	if( $array[$count] =~ /testdriver/i )
	{
		$pid_td = $array[$count-1];
		last;
	}
	$count++;
}
if( $pid_td != 0 )
{
	system("ntsd -c q -p $pid_td");
	print "kill the process $pid_td";
}
1;
