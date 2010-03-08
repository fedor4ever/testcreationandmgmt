<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:CommDB="SymbianOS.Generic.Comms-Infras.CommDB">

	<xsl:output method="xml" version="1.0" encoding="iso-8859-1" indent="yes" />

	<xsl:template match="*">		
		<xsl:copy>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>

	<xsl:template match="//CommDB:IAPTable" xmlns="SymbianOS.Generic.Comms-Infras.CommDB">
		<IAPTable>
			<xsl:copy-of select="*"/>
			<xsl:if test="not(contains(.,'WinTAP'))">
				<IAP operation="add">
					<Name>Ethernet WinTAP</Name>
					<IAPService>LANService.Ethernet WinTAP</IAPService>
					<IAPBearer>LANBearer.Ethernet WinTAP</IAPBearer>
					<IAPNetwork>Network.Intranet</IAPNetwork>
					<IAPNetworkWeighting>0</IAPNetworkWeighting>
					<LocationRef>Location.Office</LocationRef>
				</IAP>
			</xsl:if>
		</IAPTable>
	</xsl:template>

	<xsl:template match="//CommDB:LANBearerTable" xmlns="SymbianOS.Generic.Comms-Infras.CommDB">
		<LANBearerTable>
			<xsl:copy-of select="*"/>
			<xsl:if test="not(contains(.,'WinTAP'))">
				<LANBearer operation="add">
					<Name>Ethernet WinTAP</Name>
					<Agent>nullagt.agt</Agent>
					<IfName>ethint</IfName>
					<LDDFilename>enet</LDDFilename>
					<LDDName>=Ethernet</LDDName>
					<PDDFilename>ethertap</PDDFilename>
					<PDDName>Ethertap.Wins</PDDName>
					<PacketDriverName>EtherPkt.drv</PacketDriverName>
					<LastSocketActivityTimeout>-1</LastSocketActivityTimeout>
					<LastSessionClosedTimeout>-1</LastSessionClosedTimeout>
					<LastSocketClosedTimeout>-1</LastSocketClosedTimeout>
				</LANBearer>
			</xsl:if>
		</LANBearerTable>
	</xsl:template>

	<xsl:template match="//CommDB:LANServiceTable" xmlns="SymbianOS.Generic.Comms-Infras.CommDB">
		<LANServiceTable>
			<xsl:copy-of select="*"/>
			<xsl:if test="not(contains(.,'WinTAP'))">
				<LANService operation="add">
					<Name>Ethernet WinTAP</Name>
					<IfNetworks>ip</IfNetworks>
					<IpNetMask>255.255.255.0</IpNetMask>
					<IpGateway>192.168.0.1</IpGateway>
					<IpAddrFromServer>FALSE</IpAddrFromServer>
					<IpAddr>192.168.0.3</IpAddr>
					<IpDNSAddrFromServer>FALSE</IpDNSAddrFromServer>
					<IpNameServer1>10.16.59.15</IpNameServer1>
					<IpNameServer2>10.23.58.12</IpNameServer2>
					<Ip6DNSAddrFromServer>FALSE</Ip6DNSAddrFromServer>
				</LANService>
			</xsl:if>
		</LANServiceTable>
	</xsl:template>

</xsl:stylesheet>
