<?xml-stylesheet type="text/xsl" href="http://www.w3.org/1999/Transform"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/Transform"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:report="http://www.symbian.com/DriverReport">
	<xsl:output method="xml"/>
	<!-- MUST use a MAP file to declare all TEF XML files -->
	
	<xsl:template match="/">
		
		<report>
			<xsl:apply-templates select="//message" />
		</report>
	
	</xsl:template>
	
	<xsl:template match="message" >
		<aReport xsi:type="report:tefReport">
		</aReport>
	</xsl:template>
</xsl:stylesheet>