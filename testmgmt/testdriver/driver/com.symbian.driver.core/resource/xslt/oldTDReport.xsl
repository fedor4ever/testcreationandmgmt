<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:output method="html" version="4.0" encoding="iso-8859-1" indent="yes" />

	<xsl:template match="/">
		<head>
			<script langauge="JavaScript" type="text/javascript">
			
function doMenu(item)
{
    var obj = getElementsByName_iefix("div", item);
    var col = document.getElementById("x" + item);

    for (var i = 0; i &lt; obj.length; i++)
    {
        if (obj[i].style.display=="none")
        {
            obj[i].style.display="block";
            col.innerHTML="[-]";
        }
        else
        {
            obj[i].style.display="none";
            col.innerHTML="[+]";
        }
    }
}

function getElementsByName_iefix(tag, name)
{
    var elem = document.getElementsByTagName(tag);
    var arr = new Array();
    for (var i = 0, iarr = 0; i &lt; elem.length; i++)
    {
        att = elem[i].getAttribute("name");
        if (att == name)
        {
            arr[iarr] = elem[i];
            iarr++;
        }
    }

    return arr;
}
				
			</script>
		</head>
		<body>
			<h1>TestDriver Results</h1>
			<xsl:apply-templates select="//reportInfo" />
			<h2>Results</h2>
			<xsl:if test="//aReport[@xsi:type='report:tefReport']">
				<h3>TestExecute Results</h3>
				<br />
				<table border="1">
					<tr>
						<th>Task</th>
						<th>Test Script Name</th>
						<th>Duration</th>
						<th>Log</th>
						<th>Test Case Results</th>
						<th>Test Step Results</th>
						<th>Run Program Results</th>
						<th>Timedout</th>
						<xsl:if test="//info[@key='hasCoreDump']/@value='true'">
						    <th>Crash</th>
						    <th>CoreDump</th>
					        </xsl:if>
					</tr>
					<xsl:apply-templates select="//aReport[@xsi:type='report:tefReport']" />
				</table>
			</xsl:if>
			<xsl:if test="//aReport[@xsi:type='report:genericReport']">
				<h3>Other Results</h3>
				<br />
				<table border="1">
					<tr>
						<th>Task</th>
						<th>Discription</th>
						<th>Log</th>
						<xsl:if test="//info[@key='hasTrace']/@value='true'">
						    <th>Trace</th>
						</xsl:if>
						<xsl:if test="//info[@key='hasCoreDump']/@value='true'">
						    <th>CoreDump</th>
						</xsl:if>
						<th>Pass</th>
					</tr>
					<xsl:apply-templates select="//aReport[@xsi:type='report:genericReport']" />
				</table>
			</xsl:if>
		</body>
	</xsl:template>

	<xsl:template match="reportInfo">
		<xsl:variable name="attemptedTestStep" select="//info[@key = 'attemptedTestStep']/@value" />
		<xsl:variable name="passedTestStep" select="//info[@key = 'passedTestStep']/@value" />
		<xsl:variable name="failedTestStep" select="//info[@key = 'failedTestStep']/@value" />
		<xsl:variable name="unkownTestStep" select="//info[@key = 'unkownTestStep']/@value" />

		<h2>Run Info</h2>
		<table border="0">
			<tr>
				<th>Date:</th>
				<td>
					<xsl:value-of select="//info[@key = 'date']/@value" />
				</td>
			</tr>
			<tr>
				<th>Run Number:</th>
				<td>
					<xsl:value-of select="//info[@key = 'runNumber']/@value" />
				</td>
			</tr>
			<tr>
				<th>Platfom:</th>
				<td>
					<xsl:value-of select="//info[@key = 'platform']/@value" />
				</td>
			</tr>
			<tr>
				<th>Build:</th>
				<td>
					<xsl:value-of select="//info[@key = 'build']/@value" />
				</td>
			</tr>
			<tr>
				<th>Transport:</th>
				<td>
					<xsl:value-of select="//info[@key = 'transport']/@value" />
				</td>
			</tr>
			<tr>
				<th>Root Address:</th>
				<td>
					<xsl:value-of select="//info[@key = 'rootAddress']/@value" />
				</td>
			</tr>
			<tr>
				<th>Build Number:</th>
				<td>
					<xsl:value-of select="//info[@key = 'buildNumber']/@value" />
				</td>
			</tr>
			<tr>
				<th>Number of Test Scripts:</th>
				<td>
					<xsl:value-of select="//info[@key = 'testCount']/@value" />
				</td>
			</tr>
			<tr>
				<th>Number of Test Steps:</th>
				<td>
					<xsl:value-of select="$attemptedTestStep" />
				</td>
			</tr>
			<tr>
				<th>Test Steps Passed:</th>
				<td>
					<xsl:value-of select="$passedTestStep" />/<xsl:value-of select="$attemptedTestStep" /> (<xsl:value-of select="round(($passedTestStep div $attemptedTestStep) * 100)" />%)
				</td>
			</tr>
			<tr>
				<th>Test Steps Failed:</th>
				<td>
					<xsl:value-of select="$failedTestStep" />/<xsl:value-of select="$attemptedTestStep" /> (<xsl:value-of select="round(($failedTestStep div $attemptedTestStep) * 100)" />%)
				</td>
			</tr>
			<tr>
				<th>Test Steps Unkown:</th>
				<td>
					<xsl:value-of select="$unkownTestStep" />/<xsl:value-of select="$attemptedTestStep" /> (<xsl:value-of select="round(($unkownTestStep div $attemptedTestStep) * 100)" />%)
				</td>
			</tr>
		</table>
	</xsl:template>

	<xsl:template match="aReport[@xsi:type='report:tefReport']">
		<xsl:variable name="testStepCount" select="tefTestStepSummary/@count" />
		<xsl:variable name="testCaseCount" select="tefTestCaseSummary/@count" />
		<xsl:variable name="runProgramCount" select="tefTestRunWsProgramSummary/@count" />

		<tr>
			<td>
				<xsl:value-of select="@task" />
			</td>
			<td>
				<xsl:value-of select="@name" />
			</td>
			<td>
				<xsl:value-of select="@duration" />
			</td>
			<td>
				<a>
					<xsl:attribute name="href">
						<xsl:value-of select="@log" />
					</xsl:attribute>Log
				</a>
			</td>

			<td align="middle">
				<xsl:choose>
					<xsl:when test="tefTestCaseSummary">
						<a>
							<xsl:attribute name="href">javascript:doMenu('tefTestCase<xsl:value-of select="@name" />')</xsl:attribute>
							<xsl:attribute name="id">xtefTestCase<xsl:value-of select="@name" /></xsl:attribute>
							[+]
						</a>

						<xsl:choose>
							<xsl:when test="tefTestCaseSummary/@pass &gt; 0 and tefTestCaseSummary/@fail = 0 and tefTestCaseSummary/@inconclusive = 0">
								<font color="green">Pass: <xsl:value-of select="tefTestCaseSummary/@pass" /> (<xsl:value-of select="round((tefTestCaseSummary/@pass div $testCaseCount) * 100)" />%)</font>
							</xsl:when>
							<xsl:otherwise>
								<font color="red">Pass: <xsl:value-of select="tefTestCaseSummary/@pass" /> (<xsl:value-of select="round((tefTestCaseSummary/@pass div $testCaseCount) * 100)" />%)</font>
							</xsl:otherwise>
						</xsl:choose>
						<xsl:if test="tefTestCaseSummary/@fail &gt; 0">
							, <font color="red">Fail: <xsl:value-of select="tefTestCaseSummary/@fail" /> (<xsl:value-of select="round((tefTestCaseSummary/@fail div $testCaseCount) * 100)" />%)</font>
						</xsl:if>
						<xsl:if test="tefTestCaseSummary/@inconclusive &gt; 0">
							, <font color="red">Inconclusive: <xsl:value-of select="tefTestCaseSummary/@inconclusive" /> (<xsl:value-of select="round((tefTestCaseSummary/@inconclusive div $testCaseCount) * 100)" />%)
							</font>
						</xsl:if>
						<xsl:if test="tefTestCaseSummary/@skipped_selectively &gt; 0">
							, <font color="gray">Skipped: <xsl:value-of select="tefTestCaseSummary/@skipped_selectively" /> (<xsl:value-of select="round((tefTestCaseSummary/@skipped_selectively div $testCaseCount) * 100)" />%)
							</font>
						</xsl:if>
						

						<div>
							<xsl:attribute name="name">tefTestCase<xsl:value-of select="@name" /></xsl:attribute>
							<xsl:attribute name="style">display:none</xsl:attribute>
							
							<table border="1" cellpadding="5" frame="void">
								<tr>
									<th>Test Case</th>
									<th>Result</th>
								</tr>
								<xsl:for-each select="tefTestCaseSummary/testCase">
									<tr>
										<td>
											<xsl:value-of select="@name" />
										</td>
										<td>
											<xsl:choose>
												<xsl:when test="@result = 'pass'">
													<font color="green"><xsl:value-of select="@result" /></font>
												</xsl:when>
												<xsl:when test="@result = 'skipped_selectively'">
													<font color="gray"><xsl:value-of select="@result" /></font>
												</xsl:when>
												<xsl:otherwise>
													<xsl:value-of select="@result" />
												</xsl:otherwise>
											</xsl:choose>
										</td>
									</tr>
								</xsl:for-each>
							</table>
						</div>
					</xsl:when>
					<xsl:otherwise>N/A</xsl:otherwise>
				</xsl:choose>
			</td>

			<td align="middle">
				<xsl:choose>
					<xsl:when test="tefTestStepSummary/@pass">
						<a>
							<xsl:attribute name="href">javascript:doMenu('tefTestStep<xsl:value-of select="@name" />')</xsl:attribute>
							<xsl:attribute name="id">xtefTestStep<xsl:value-of select="@name" /></xsl:attribute>
							[+]
						</a>
		
						<xsl:choose>
							<xsl:when
								test="tefTestStepSummary/@pass &gt; 0 and tefTestStepSummary/@fail = 0 and tefTestStepSummary/@unexecuted = 0 and tefTestStepSummary/@unknown = 0 and tefTestStepSummary/@abort = 0 and tefTestStepSummary/@panic = 0 and tefTestStepSummary/@inconclusive = 0">
								<font color="green">
									Pass: <xsl:value-of select="tefTestStepSummary/@pass" /> (<xsl:value-of select="round((tefTestStepSummary/@pass div $testStepCount) * 100)" />%)
								</font>
							</xsl:when>
							<xsl:otherwise>
								<font color="red">Pass: <xsl:value-of select="tefTestStepSummary/@pass" /> (<xsl:value-of select="round((tefTestStepSummary/@pass div $testStepCount) * 100)" />%)</font>
							</xsl:otherwise>
						</xsl:choose>
						<xsl:if test="tefTestStepSummary/@fail &gt; 0">
							, <font color="red">Fail: <xsl:value-of select="tefTestStepSummary/@fail" /> (<xsl:value-of select="round((tefTestStepSummary/@fail div $testStepCount) * 100)" />%)</font>
						</xsl:if>
						<xsl:if test="tefTestStepSummary/@unexecuted &gt; 0">
							, <font color="red">Unexecuted: <xsl:value-of select="tefTestStepSummary/@unexecuted" /> (<xsl:value-of select="round((tefTestStepSummary/@unexecuted div $testStepCount) * 100)" />%)</font>
						</xsl:if>
						<xsl:if test="tefTestStepSummary/@unknown &gt; 0">
							, <font color="red">Unkown: <xsl:value-of select="tefTestStepSummary/@unknown" /> (<xsl:value-of select="round((tefTestStepSummary/@unknown div $testStepCount) * 100)" />%)</font>
						</xsl:if>
						<xsl:if test="tefTestStepSummary/@abort &gt; 0">
							, <font color="red">Abort: <xsl:value-of select="tefTestStepSummary/@abort" /> (<xsl:value-of select="round((tefTestStepSummary/@abort div $testStepCount) * 100)" />%)</font>
						</xsl:if>
						<xsl:if test="tefTestStepSummary/@panic &gt; 0">
							, <font color="red">Panic: <xsl:value-of select="tefTestStepSummary/@panic" /> (<xsl:value-of select="round((tefTestStepSummary/@panic div $testStepCount) * 100)" />%)</font>
						</xsl:if>
						<xsl:if test="tefTestStepSummary/@inconclusive &gt; 0">
							, <font color="red">Inconclusive: <xsl:value-of select="tefTestStepSummary/@inconclusive" /> (<xsl:value-of select="round((tefTestStepSummary/@inconclusive div $testStepCount) * 100)" />%)</font>
						</xsl:if>
		
						<div>
							<xsl:attribute name="name">tefTestStep<xsl:value-of select="@name" /></xsl:attribute>
							<xsl:attribute name="style">display:none</xsl:attribute>
							
							<table border="1" cellpadding="5" frame="void">
								<tr>
									<th>Test Step</th>
									<th>Result</th>
								</tr>
								<xsl:for-each select="tefTestStepSummary/testCase">
									<tr>
										<td>
											<xsl:value-of select="@name" />
										</td>
										<td>
											<xsl:choose>
												<xsl:when test="@result != 'pass'">
													<font color="red"><xsl:value-of select="@result" /></font>
												</xsl:when>
												<xsl:otherwise>
													<font color="green"><xsl:value-of select="@result" /></font>
												</xsl:otherwise>
											</xsl:choose>
										</td>
									</tr>
								</xsl:for-each>
							</table>
						</div>
					</xsl:when>
					<xsl:otherwise>N/A</xsl:otherwise>
				</xsl:choose>
			</td>
			
			<td align="middle">
				<xsl:choose>
					<xsl:when test="tefTestRunWsProgramSummary">
						<a>
							<xsl:attribute name="href">javascript:doMenu('tefRunProgram<xsl:value-of select="@name" />')</xsl:attribute>
							<xsl:attribute name="id">xtefRunProgram<xsl:value-of select="@name" /></xsl:attribute>
							[+]
						</a>

						<xsl:choose>
							<xsl:when
								test="tefTestRunWsProgramSummary/@pass &gt; 0 and tefTestRunWsProgramSummary/@fail = 0 and tefTestRunWsProgramSummary/@unexecuted = 0 and tefTestRunWsProgramSummary/@unknown = 0 and tefTestRunWsProgramSummary/@abort = 0 and tefTestRunWsProgramSummary/@panic = 0 and tefTestRunWsProgramSummary/@inconclusive = 0">
								<font color="green">
									Pass: <xsl:value-of select="tefTestRunWsProgramSummary/@pass" /> (<xsl:value-of select="round((tefTestRunWsProgramSummary/@pass div $runProgramCount) * 100)" />%)
								</font>
							</xsl:when>
							<xsl:otherwise>
								<font color="red">Pass: <xsl:value-of select="tefTestRunWsProgramSummary/@pass" /> (<xsl:value-of select="round((tefTestRunWsProgramSummary/@pass div $runProgramCount) * 100)" />%)</font>
							</xsl:otherwise>
						</xsl:choose>
						<xsl:if test="tefTestRunWsProgramSummary/@fail &gt; 0">
							, <font color="red">Fail: <xsl:value-of select="tefTestRunWsProgramSummary/@fail" /> (<xsl:value-of select="round((tefTestRunWsProgramSummary/@fail div $runProgramCount) * 100)" />%)</font>
						</xsl:if>
						<xsl:if test="tefTestRunWsProgramSummary/@unexecuted &gt; 0">
							, <font color="red">Unexecuted: <xsl:value-of select="tefTestRunWsProgramSummary/@unexecuted" /> (<xsl:value-of select="round((tefTestRunWsProgramSummary/@unexecuted div $runProgramCount) * 100)" />%)</font>
						</xsl:if>
						<xsl:if test="tefTestRunWsProgramSummary/@unknown &gt; 0">
							, <font color="red">Unkown: <xsl:value-of select="tefTestRunWsProgramSummary/@unknown" /> (<xsl:value-of select="round((tefTestRunWsProgramSummary/@unknown div $runProgramCount) * 100)" />%)</font>
						</xsl:if>
						<xsl:if test="tefTestRunWsProgramSummary/@abort &gt; 0">
							, <font color="red">Abort: <xsl:value-of select="tefTestRunWsProgramSummary/@abort" /> (<xsl:value-of select="round((tefTestRunWsProgramSummary/@abort div $runProgramCount) * 100)" />%)</font>
						</xsl:if>
						<xsl:if test="tefTestRunWsProgramSummary/@panic &gt; 0">
							, <font color="red">Panic: <xsl:value-of select="tefTestRunWsProgramSummary/@panic" /> (<xsl:value-of select="round((tefTestRunWsProgramSummary/@panic div $runProgramCount) * 100)" />%)</font>
						</xsl:if>
						<xsl:if test="tefTestRunWsProgramSummary/@inconclusive &gt; 0">
							, <font color="red">Inconclusive: <xsl:value-of select="tefTestRunWsProgramSummary/@inconclusive" /> (<xsl:value-of select="round((tefTestRunWsProgramSummary/@inconclusive div $runProgramCount) * 100)" />%)</font>
						</xsl:if>
		
						<div>
							<xsl:attribute name="name">tefRunProgram<xsl:value-of select="@name" /></xsl:attribute>
							<xsl:attribute name="style">display:none</xsl:attribute>
							
							<table border="1" cellpadding="5" frame="void">
								<tr>
									<th>Program</th>
									<th>Result</th>
								</tr>
								<xsl:for-each select="tefTestRunWsProgramSummary/testCase">
									<tr>
										<td>
											<xsl:value-of select="@name" />
										</td>
										<td>
											<xsl:choose>
												<xsl:when test="@result != 'pass'">
													<font color="red"><xsl:value-of select="@result" /></font>
												</xsl:when>
												<xsl:otherwise>
													<xsl:value-of select="@result" />
												</xsl:otherwise>
											</xsl:choose>
										</td>
									</tr>
								</xsl:for-each>
							</table>
						</div>
					</xsl:when>
					<xsl:otherwise>N/A</xsl:otherwise>
				</xsl:choose>
			</td>
			
			<td>
				<xsl:choose>
					<xsl:when test="@timedout = 'true'">
						<font color="red">Yes</font>
					</xsl:when>
					<xsl:otherwise>
						<font color="green">No</font>
					</xsl:otherwise>
				</xsl:choose>
			</td>
		        <xsl:if test="//info[@key='hasCoreDump']/@value='true'">	
			<td>
				<xsl:choose>
					<xsl:when test="@crash = 'true'">
						<font color="red">AppCrash</font>
					</xsl:when>
					<xsl:otherwise>
						<font color="green">No</font>
					</xsl:otherwise>
				</xsl:choose>
			</td>
			
			<td>
			    <xsl:choose>
					<xsl:when test="@coredump != ''">
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="@coredump" />
							</xsl:attribute>CoreDump
						</a>
					</xsl:when>
					<xsl:otherwise>-</xsl:otherwise>
				</xsl:choose>
			</td>
			</xsl:if>
			
		</tr>
	</xsl:template>

	<xsl:template match="aReport[@xsi:type='report:genericReport']">
		<tr>
			<td>
				<xsl:value-of select="@task" />
			</td>
			<td>
				<xsl:value-of select="@name" />
			</td>
			<td>
				<xsl:choose>
					<xsl:when test="@log != ''">
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="@log" />
							</xsl:attribute>Log
						</a>
					</xsl:when>
					<xsl:otherwise>-</xsl:otherwise>
				</xsl:choose>
			</td>
			
			<xsl:if test="//info[@key='hasTrace']/@value='true'">
			<td>
				<xsl:choose>
					<xsl:when test="@trace != ''">
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="@trace" />
							</xsl:attribute>Trace
						</a>
					</xsl:when>
					<xsl:otherwise>-</xsl:otherwise>
				</xsl:choose>
			</td>
			</xsl:if>
			<xsl:if test="//info[@key='hasCoreDump']/@value='true'">
			<td>
				<xsl:choose>
					<xsl:when test="@coredump != ''">
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="@coredump" />
							</xsl:attribute>CoreDump
						</a>
					</xsl:when>
					<xsl:otherwise>-</xsl:otherwise>
				</xsl:choose>
			</td>
			</xsl:if>
			<td>
				<xsl:choose>
					<xsl:when test="@result = 'error'">
						<font color="red">Error</font>
					</xsl:when>
					<xsl:when test="@result = 'warning'">
						<font color="orange">Warning</font>
					</xsl:when>
					<xsl:when test="@crash = 'true'">
					    <font color="red">AppCrash</font>
					</xsl:when>
					<xsl:otherwise>
						<font color="green">Pass</font>
					</xsl:otherwise>
				</xsl:choose>
			</td>
		</tr>
	</xsl:template>

</xsl:stylesheet>
