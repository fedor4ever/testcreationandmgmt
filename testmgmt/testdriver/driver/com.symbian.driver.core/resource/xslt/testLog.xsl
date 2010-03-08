<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:lxslt="http://xml.apache.org/xslt"
	xmlns:td="TestDriver"
	extension-element-prefixes="td">
<xsl:output method="text" encoding="iso-8859-1"/>
<xsl:template match="/"><xsl:for-each select="//aReport">
<xsl:if test="@log != ''">2005-01-01=01:01:01 Running &lt;<xsl:value-of select="@name" />&gt; for urel
2005-01-01=01:01:01   Log location: &lt;<xsl:value-of select="@log" />&gt;
</xsl:if>
</xsl:for-each></xsl:template></xsl:stylesheet>