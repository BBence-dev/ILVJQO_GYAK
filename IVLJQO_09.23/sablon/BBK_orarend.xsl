<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2>Órarend III. évf. Mérnök info. (BSc) Korszerű WEB tech. 5BIL W</h2>
				<table border="1 solid">
					<tr bgcolor="lightgreen">
						<th>Tárgy</th>
						<th>Nap</th>
						<th>Dátum</th>
						<th>Kezdés</th>
						<th>Vége</th>
						<th>Helyszin</th>
						<th>Oktató</th>
					</tr>
					<xsl:for-each select="orarend/ora">
					<tr>
						<td><xsl:value-of select="targy"></xsl:value-of></td>
						<td><xsl:value-of select="idopont/nap"></xsl:value-of></td>
						<td><xsl:value-of select="idopont/datum"></xsl:value-of></td>
						<td><xsl:value-of select="idopont/tol"></xsl:value-of></td>
						<td><xsl:value-of select="idopont/ig"></xsl:value-of></td>
						<td><xsl:value-of select="helyszin"></xsl:value-of></td>
						<td><xsl:value-of select="oktato"></xsl:value-of></td>
					</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet> 