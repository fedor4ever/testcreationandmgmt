/*
* Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies). 
* All rights reserved.
* This component and the accompanying materials are made available
* under the terms of "Eclipse Public License v1.0"
* which accompanies this distribution, and is available
* at the URL "http://www.eclipse.org/legal/epl-v10.html".
*
* Initial Contributors:
* Nokia Corporation - initial contribution.
*
* Contributors:
*
* Description:
*
*/


package com.nokia.s60tools.stif.configmanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;


/**
 * STIF configuration file menagment tool
 * 
 */
public class ConfigManager {
	
	/**
	 * STIF configuration file section standard representation
	 * 
	 */
	private static class Section {
		/**
		 * Section begin line number
		 */
		public int beginLine = -1;
		/**
		 * Section end line number
		 */
		public int endLine = -1;
		/**
		 * List of section elements
		 */
		public ArrayList<SectionElement> elements = null;
	}
	
	/**
	 * STIF configuration file module section representation
	 * 
	 */
	private static class ModuleSection {
		/**
		 * Section begine line number
		 */
		public int beginLine = -1;
		/**
		 * Section end line number
		 */
		public int endLine = -1;
		/**
		 * Module name element
		 */
		SectionElement name = null;
		/**
		 * Module ini file element
		 */
		SectionElement iniFile = null;
		/**
		 * List of module testcase files
		 */
		public ArrayList<SectionElement> testCaseFiles = null;		
	}
	
	/**
	 * STIF configuration file section element representation
	 * 
	 */
	private static class SectionElement {

		/**
		 * Element line number
		 */
		public int lineNumber = -1;
		/**
		 * Element type
		 */
		public SectionElementType type = SectionElementType.UNDEFINED;
		/**
		 * Element value
		 */
		public String value = "";
	}
	
	/**
	 * Module section type id
	 */
	private static final int MODULE_SECTION = 3;
	/**
	 * Logger defaults section id
	 */
	private static final int LOGGER_DEFAULTS_SECTION = 2;
	/**
	 * Engine defaults section id
	 */
	private static final int ENGINE_DEFAULTS_SECTION = 1;
	/**
	 * Element outside section id
	 */
	private static final int OUTSIDE_SECTION = 0;
	/**
	 * Engine defaults section begine tag
	 */
	private static final String ENGINE_DEFAULTS_SECTION_BEGIN_TAG = "[Engine_Defaults]";
	/**
	 * Engine defaults section end tag
	 */
	private static final String ENGINE_DEFAULTS_SECTION_END_TAG = "[End_Defaults]";	
	/**
	 * Module section begine tag
	 */
	private static final String MODULE_SECTION_BEGIN_TAG = "[New_Module]";
	/**
	 * Module sectio end tag
	 */
	private static final String MODULE_SECTION_END_TAG = "[End_Module]";						
	/**
	 * Logger defaults section begine tag
	 */
	private static final String LOGGER_DEFAULTS_SECTION_BEGIN_TAG = "[Logger_Defaults]";
	/**
	 * Logger defaults section end tag
	 */
	private static final String LOGGER_DEFAULTS_SECTION_END_TAG = "[End_Logger_Defaults]";			
	/**
	 * Configuration file source
	 */
	private String configSource = null;
	/**
	 * List of modules present in configuration file
	 */
	private ArrayList<ModuleSection> modules = null;
	/**
	 * Representation of engine defaults section present in configuration file
	 */
	private Section engineDefaults = null;
	/**
	 * Representation of logger defaults section present in configuration file
	 */
	private Section loggerDefaults = null;		
	/**
	 * List of problems which apperas during configuration file parsing
	 */
	private ArrayList<ParseProblem> parseProblems = null;
		
	/**
	 * Parese STIF configuration file
	 * @param config configuration file source
	 */
	public void parseConfig( String config )
	{
		engineDefaults = new Section();
		modules = new ArrayList<ModuleSection>();
		loggerDefaults = new Section();		
		parseProblems = new ArrayList<ParseProblem>();
		configSource = config;
		
		// Create config reader to be able to read lines from config
		BufferedReader configReader = new BufferedReader(new StringReader(configSource));
		
		// Config parsing
		int lineNumber = 0;
		int sectionType = OUTSIDE_SECTION;	// Current section type:
											//	0 - outside section
											//	1 - Engine defaults section
											//	2 - Logger defaults section
											//	3 - Module section
		
		try {
			while( true )
			{
				// Get line from config
				String line = configReader.readLine();
				if ( line == null ) {
					// End of config, stop parsing
					break;
				}
				// Remove white spaces at the begining end end of line
								
				line = line.trim();
				lineNumber++;
				
				// Remove comments from line
				int commentStartPos = -1;
				if( sectionType == OUTSIDE_SECTION )
				{
					// Outside section only # comments are allowed
					commentStartPos = line.indexOf('#');
					if ( commentStartPos != -1 ) {
						line = line.substring(0,commentStartPos);
					}					
				}
				else
				{
					// Inside section # and // comments are allowed
					commentStartPos = line.indexOf("//");
					if ( commentStartPos != -1 ) {
						line = line.substring(0,commentStartPos);
					}
					commentStartPos = line.indexOf('#');
					if ( commentStartPos != -1 ) {
						line = line.substring(0,commentStartPos);
					}					
				}
				// If line is emepty parse next line
				line = line.trim();
				if ( line.length() == 0 ) {
					continue;
				}
				

				boolean newSectionFound = false;
				int newSectionType = -1;
				int tagLength = 0;
				if(line.startsWith(ENGINE_DEFAULTS_SECTION_BEGIN_TAG)) {
					newSectionFound = true;
					newSectionType = ENGINE_DEFAULTS_SECTION;						
					tagLength = ENGINE_DEFAULTS_SECTION_BEGIN_TAG.length();
					engineDefaults.beginLine = lineNumber;
					engineDefaults.elements = new ArrayList<SectionElement>();
				} else if (line.startsWith(LOGGER_DEFAULTS_SECTION_BEGIN_TAG)) {
					newSectionFound = true;
					newSectionType = LOGGER_DEFAULTS_SECTION;
					tagLength = LOGGER_DEFAULTS_SECTION_BEGIN_TAG.length();
					loggerDefaults.beginLine = lineNumber;
					loggerDefaults.elements = new ArrayList<SectionElement>();
				} else if (line.startsWith(MODULE_SECTION_BEGIN_TAG)) {
					newSectionFound = true;
					newSectionType = MODULE_SECTION;
					tagLength = MODULE_SECTION_BEGIN_TAG.length();
					ModuleSection module = new ModuleSection();
					module.beginLine = lineNumber;
					module.testCaseFiles = new ArrayList<SectionElement>();
					modules.add(module);
				}
				if ( newSectionFound == true ) {
					if ( sectionType != OUTSIDE_SECTION ) {
						String message = "";
						if ( sectionType == ENGINE_DEFAULTS_SECTION ) {
							message = "Engine_Defaults section is not closed";					
						} else if ( sectionType == LOGGER_DEFAULTS_SECTION ) {
							message = "Logger_Defaults section is not closed";					
						} else if ( sectionType == MODULE_SECTION ) {
							message = "Module section is not closed";					
						}
						addParseProblem( ParseProblem.ProblemType.ERROR, lineNumber, message );
					}						
					sectionType = newSectionType;
					String afterTag = line.substring(tagLength,line.length());
					if ( afterTag != null ) {
						afterTag = afterTag.trim();
						if ( afterTag.length() != 0 ) {
							String message = "Unknown text after section definition: " + afterTag;
							addParseProblem( ParseProblem.ProblemType.WARNING, lineNumber, message );
						}								
					}					
					continue;
				} else if ( sectionType == OUTSIDE_SECTION ) {
					String message = "Unknown section type: " + line;
					addParseProblem( ParseProblem.ProblemType.ERROR, lineNumber, message );
					continue;
				}
				
				switch( sectionType ) {
				case ENGINE_DEFAULTS_SECTION:
					{
						if (line.startsWith(ENGINE_DEFAULTS_SECTION_END_TAG)) {
							sectionType = OUTSIDE_SECTION;
							engineDefaults.endLine = lineNumber;
							continue;
						}
						
						boolean found = false;
						SectionElementType[] allowedElements = ConfigUtil.getAllowedEngineDefaultsSectionElements();
						for ( int s = 0; s < allowedElements.length; s ++ ) {
							String tag = ConfigUtil.getEngineDefaultsSectionElementTag( 
									allowedElements[ s ] );
							if ( line.startsWith(tag) ) {
								SectionElement element = new SectionElement();													
								element.type = allowedElements[ s ];
								element.value = line.substring(tag.length(),line.length()).trim();
								element.lineNumber = lineNumber;								
								if ( !( ConfigUtil.hasProperValue( element.type, element.value ) ) ) {
									String message = tag.substring( 0, tag.length() - 1 ) + " - Invalid element value: " + element.value;
									addParseProblem( ParseProblem.ProblemType.ERROR, lineNumber, message );
								}								
								engineDefaults.elements.add(element);
								found = true;
								break;
							}
						}
						if ( !found ) {
							String message = "Unknown keyword in Engine_Defaults section: " + line;
							addParseProblem( ParseProblem.ProblemType.ERROR, lineNumber, message );
						}
					}
					break;
				case LOGGER_DEFAULTS_SECTION:
					{
						if (line.startsWith(LOGGER_DEFAULTS_SECTION_END_TAG)) {
							sectionType = OUTSIDE_SECTION;
							loggerDefaults.endLine = lineNumber;
							continue;
						}
						
						boolean found = false;
						SectionElementType[] allowedElements = ConfigUtil.getAllowedLoggerDefaultsSectionElements();
						for ( int s = 0; s < allowedElements.length; s ++ ) {
							String tag = ConfigUtil.getLoggerDefaultsSectionElementTag( 
									allowedElements[ s ] );
							if ( line.startsWith(tag) ) {
								SectionElement element = new SectionElement();													
								element.type = allowedElements[ s ];
								element.value = line.substring(tag.length(),line.length()).trim();
								element.lineNumber = lineNumber;
								if ( !( ConfigUtil.hasProperValue( element.type, element.value ) ) ) {
									String message = tag.substring( 0, tag.length() - 1 ) + " - Invalid element value: " + element.value;
									addParseProblem( ParseProblem.ProblemType.ERROR, lineNumber, message );
								}
								loggerDefaults.elements.add(element);
								found = true;
								break;
							}
						}
						if ( !found ) {
							String message = "Unknown keyword in Logger_Defaults section: " + line;
							addParseProblem( ParseProblem.ProblemType.ERROR, lineNumber, message );
						}
					}
					break;
				case MODULE_SECTION:
					{
						ModuleSection module = modules.get(modules.size() - 1);						
						if (line.startsWith(MODULE_SECTION_END_TAG)) {
							sectionType = OUTSIDE_SECTION;
							if( module != null ) {
								module.endLine = lineNumber;
							}
							continue;
						} 
						
						boolean found = false;
						SectionElementType[] allowedElements = ConfigUtil.getAllowedModuleSectionElements();
						for ( int s = 0; s < allowedElements.length; s ++ ) {
							String tag = ConfigUtil.getModuleSectionElementTag( 
									allowedElements[ s ] );
							if ( line.startsWith(tag) ) {
								SectionElement element = new SectionElement();													
								element.type = allowedElements[ s ];
								element.value = line.substring(tag.length(),line.length()).trim();
								element.lineNumber = lineNumber;
								
								if ( !( ConfigUtil.hasProperValue( element.type, element.value ) ) ) {
									String message = tag.substring( 0, tag.length() - 1 ) + " - Invalid element value: " + element.value;
									addParseProblem( ParseProblem.ProblemType.ERROR, lineNumber, message );
								}
								if ( element.type == SectionElementType.MODULE_NAME ) {
									module.name = element;
								} else if ( element.type == SectionElementType.INI_FILE ) {
									module.iniFile = element;
								} else if ( element.type == SectionElementType.TEST_CASE_FILE ) {
									module.testCaseFiles.add(element);	
								}													
								found = true;
								break;
							}
						}
						if ( !found ) {
							String message = "Unknown keyword in New_module section: " + line;
							addParseProblem( ParseProblem.ProblemType.ERROR, lineNumber, message );
						}							
					}
					break;					
				}
			}
			if ( sectionType != OUTSIDE_SECTION ) {
				String message = "";
				if ( sectionType == ENGINE_DEFAULTS_SECTION ) {
					message = "Engine_Defaults section is not closed";					
				} else if ( sectionType == LOGGER_DEFAULTS_SECTION ) {
					message = "Logger_Defaults section is not closed";					
				} else if ( sectionType == MODULE_SECTION ) {
					message = "Module section is not closed";					
				}
				addParseProblem( ParseProblem.ProblemType.ERROR, lineNumber, message );
			}
			
		} catch (IOException e) {
			// do nothing, end of config reached
		}		
	}

	/**
	 * Adds new problem to parsing problems list
	 * @param type type of problem
	 * @param lineNumber line number in which problem occurs
	 * @param message problem description
	 */
	private void addParseProblem( ParseProblem.ProblemType type, int lineNumber, String message ) {
		ParseProblem problem = new ParseProblem();
		problem.type = type;
		problem.lineNumber = lineNumber;
		problem.description = message;
		parseProblems.add(problem);
	}
	
	/**
	 * Gets list of problems which apperas during configuration file parsing
	 * @return list of parse problems
	 */
	public ArrayList<ParseProblem> getParseProblems() {
		return parseProblems;
	}

	/**
	 * Gets engine default section element value
	 * @param elementType type of elements which value will be returned 
	 * @return element value
	 */
	private String getEngineDefaultsElementValue( SectionElementType elementType ) {
		if ( engineDefaults.elements == null ) {
			return null;
		}
		
		int elementsCount = engineDefaults.elements.size();
		for ( int i = 0; i < elementsCount; i++ ) {
			SectionElement element = engineDefaults.elements.get(i); 
			if ( element.type == elementType ) {
				return element.value;
			}
		}
		
		return null;
	}
	
	/**
	 * Sets engine defaults section element value
	 * @param elementType type of elements which value will be updated
	 * @param value new element value
	 */
	private void setEngineDefaultsElementValue( SectionElementType elementType, String value ) {
		if ( engineDefaults.elements == null ) {
			if ( engineDefaults.beginLine == -1 ) {
				// Create {Engine_Defaults] section
				createSection(1, ENGINE_DEFAULTS_SECTION_BEGIN_TAG, ENGINE_DEFAULTS_SECTION_END_TAG);
				parseConfig(configSource);
			} else {
				engineDefaults.elements = new ArrayList<SectionElement>();
			}
		}
		
		SectionElement element = null;
		// Check if element exists in config file
		int elementsCount = engineDefaults.elements.size();
		for ( int i = 0; i < elementsCount; i++ ) {
			SectionElement tmpElement = engineDefaults.elements.get(i); 
			if ( tmpElement.type == elementType ) {
				// We found element. Stop searching. 
				element = tmpElement;
				break;
			}
		}
		
		if ( element == null ) {
			element = new SectionElement();
			element.type = elementType;
			element.value = value;
			element.lineNumber = engineDefaults.endLine;
						
			// Create new element
			createElement( element.lineNumber, 
					ConfigUtil.getEngineDefaultsSectionElementTag(element.type), value );

		} else {
			// Update existing element
			updateElement( element.lineNumber, 
					ConfigUtil.getEngineDefaultsSectionElementTag(element.type), value );
		}
	}

	/**
	 * Sets logger defaults section element value
	 * @param elementType type of elements which value will be updated
	 * @param value new element value
	 */
	private void setLoggerDefaultsElementValue( SectionElementType elementType, String value ) {
		if ( loggerDefaults.elements == null ) {
			if ( loggerDefaults.beginLine == -1 ) {
				// Create {Engine_Defaults] section
				createSection(1, LOGGER_DEFAULTS_SECTION_BEGIN_TAG, LOGGER_DEFAULTS_SECTION_END_TAG);
				parseConfig(configSource);
			} else {
				loggerDefaults.elements = new ArrayList<SectionElement>();
			}
		}
		
		SectionElement element = null;
		// Check if element exists in config file
		int elementsCount = loggerDefaults.elements.size();
		for ( int i = 0; i < elementsCount; i++ ) {
			SectionElement tmpElement = loggerDefaults.elements.get(i); 
			if ( tmpElement.type == elementType ) {
				// We found element. Stop searching. 
				element = tmpElement;
				break;
			}
		}
		
		if ( element == null ) {
			element = new SectionElement();
			element.type = elementType;
			element.value = value;
			element.lineNumber = loggerDefaults.endLine;
			if ( element.lineNumber == -1 ) {
				element.lineNumber = loggerDefaults.beginLine + 1;
			}
						
			// Create new element
			createElement( element.lineNumber, 
					ConfigUtil.getLoggerDefaultsSectionElementTag(element.type), value );

		} else {
			// Update existing element
			updateElement( element.lineNumber, 
					ConfigUtil.getLoggerDefaultsSectionElementTag(element.type), value );
		}
	}	
	
	/**
	 * Creates new element in configuration source
	 * @param lineNumber new element position
	 * @param tag new element tag
	 * @param value new element value
	 */
	private void createElement( int lineNumber, String tag, String value ) {
		BufferedReader configSourceReader = new BufferedReader(new StringReader(configSource));
		StringWriter newConfigSourceWriter = new StringWriter();
		BufferedWriter buf = new BufferedWriter( newConfigSourceWriter );
		
		try {
			int currentLineNumber = 0;
			while( true ) {
				currentLineNumber++;
				String line = configSourceReader.readLine();
				if ( line == null ) {
					// End of config source
					break;
				}
				
				if ( currentLineNumber == lineNumber ) {
					String newElement = tag + " " + value + " ";					
					buf.write(newElement);
					buf.newLine();
				}
				
				// write line to new config source buffer 
				buf.write(line);
				buf.newLine();
			}
			
			// Flush data to config writer
			buf.flush();
			// replace old config by new one
			configSource = newConfigSourceWriter.getBuffer().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		parseConfig(configSource);
	}
	
	/**
	 * Updates element value in configuration source
	 * @param lineNumber element position
	 * @param tag element tag
	 * @param value new element value
	 */
	private void updateElement( int lineNumber, String tag, String value ) {
		BufferedReader configSourceReader = new BufferedReader(new StringReader(configSource));
		StringWriter newConfigSourceWriter = new StringWriter();
		BufferedWriter buf = new BufferedWriter( newConfigSourceWriter );
		
		try {
			int currentLineNumber = 0;
			while( true ) {
				currentLineNumber++;
				String line = configSourceReader.readLine();
				if ( line == null ) {
					// End of config source
					break;
				}
				
				if ( currentLineNumber == lineNumber ) {
					String newLine = tag + " " + value + " ";
					// find comments position
					int commentTypeOnePos = line.indexOf('#');
					int commentTypeTwoPos = line.indexOf("//");
					int commentPos = -1; 
					
					if ( commentTypeOnePos > 0 ) {
						commentPos = commentTypeOnePos; 
					}
					if ( ( commentTypeOnePos > 0 ) && ( commentTypeOnePos < commentPos ) ) {
						commentPos = commentTypeTwoPos;
					}
					if ( commentPos != -1 ) {
						newLine += line.substring(commentTypeOnePos);
					}
					line = newLine;
				}
				
				// write line to new config source buffer 
				buf.write(line);
				buf.newLine();
			}
			
			// Flush data to config writer
			buf.flush();
			// replace old config by new one
			configSource = newConfigSourceWriter.getBuffer().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets logger default section element value
	 * @param elementType type of elements which value will be returned 
	 * @return element value
	 */
	private String getLoggerDefaultsElementValue( SectionElementType elementType ) {
		if ( loggerDefaults.elements == null ) {
			return null;
		}
		
		int elementsCount = loggerDefaults.elements.size();
		for ( int i = 0; i < elementsCount; i++ ) {
			SectionElement element = loggerDefaults.elements.get(i); 
			if ( element.type == elementType )
			{
				return element.value;
			}
		}
		
		return null;
	}
	
	/**
	 * Gets module name
	 * @param moduleIndex module index
	 * @return module name
	 */
	public String getModuleName( int moduleIndex ) {		
		
		return modules.get(moduleIndex).name.value;
	}

	/**
	 * Gets module ini file name
	 * @param moduleIndex module index
	 * @return module ini file name
	 */
	public String getModuleIniFile( int moduleIndex ) {		
		if ( modules.get(moduleIndex).iniFile == null )
		{
			return null;
		}
		
		return modules.get(moduleIndex).iniFile.value;
	}

	/**
	 * Gets module testcase file name
	 * @param moduleIndex module index
	 * @param configFileIndex testcase file index
	 * @return testcase file name
	 */
	public String getModuleTestCaseFile( int moduleIndex, int configFileIndex ) {		
		if ( ( modules.get(moduleIndex).testCaseFiles == null ) ||
			 (modules.get(moduleIndex).testCaseFiles.get(configFileIndex) == null) ) { 
			return null;
		}
		
		return modules.get(moduleIndex).testCaseFiles.get(configFileIndex).value;
	}
	
	/**
	 * Gets available modules count 
	 * @return modules count
	 */
	public int getModulesCount(){
		return modules.size();
	}
	
	/**
	 * Gets module testcase files conunt
	 * @param moduleIndex module index
	 * @return testcase files count
	 */
	public int getModuleTestcaseFilesCount( int moduleIndex ) {
		if ( modules.get(moduleIndex).testCaseFiles == null ) {
			return 0;
		}
		
		return modules.get(moduleIndex).testCaseFiles.size();
	}	
	
	/**
	 * Gets <code>TestReportMode</code> element value
	 * @return <code>TestReportMode</code> element value
	 */
	public TestReportMode getTestReportMode() {
		String mode = getEngineDefaultsElementValue(SectionElementType.TEST_REPORT_MODE);
		if ( mode == null )
		{
			return TestReportMode.NOT_DEFINED;
		}
		
		mode = mode.trim();
		return ConfigUtil.getTestReportModeByName( mode );
	}

	/**
	 * Sets <code>TestReportMode</code> element value
	 * @param mode new <code>TestReportMode</code> element value
	 */
	public void setTestReportMode( TestReportMode mode ) {
		String modeStr = ConfigUtil.getTestReportModeName( mode );
		setEngineDefaultsElementValue( SectionElementType.TEST_REPORT_MODE, modeStr );
	}	
	
	/**
	 * Gets <code>CreateTestReport</code> element value
	 * @return <code>CreateTestReport</code> element value
	 */
	public YesNo getCreateTestReport() {
		String value = getEngineDefaultsElementValue( SectionElementType.CREATE_TEST_REPORT);
		return ConfigUtil.getYesNoValueByName(value);
	}

	/**
	 * Sets <code>CreateTestReport</code> element value
	 * @param value new <code>CreateTestReport</code> element value
	 */
	public void setCreateTestReport( YesNo value ) {
		String valueStr = ConfigUtil.getYesNoValueName(value);
		setEngineDefaultsElementValue( SectionElementType.CREATE_TEST_REPORT , valueStr );
	}
	
	/**
	 * Gets <code>TestReportFilePath</code> element value
	 * @return <code>TestReportFilePath</code> element value
	 */
	public String getTestReportFilePath() {
		return getEngineDefaultsElementValue( SectionElementType.TEST_REPORT_FILE_PATH);
	}
	
	/**
	 * Sets <code>TestReportFilePath</code> element value
	 * @param path new <code>TestReportFilePath</code> element value
	 */
	public void setTestReportFilePath( String path ) {
		setEngineDefaultsElementValue( SectionElementType.TEST_REPORT_FILE_PATH , path );		
	}
	
	/**
	 * Gets <code>TestReportFileName</code> element value
	 * @return <code>TestReportFileName</code> element value
	 */
	public String getTestReportFileName() {
		return getEngineDefaultsElementValue(SectionElementType.TEST_REPORT_FILE_NAME);		
	}

	/**
	 * Sets <code>TestReportFileName</code> element value
	 * @param name new <code>TestReportFileName</code> element value
	 */
	public void setTestReportFileName( String name ) {
		setEngineDefaultsElementValue( SectionElementType.TEST_REPORT_FILE_NAME , name );		
	}
		
	/**
	 * Gets <code>TestReportFormat</code> element value
	 * @return <code>TestReportFormat</code> element value
	 */
	public OutputFileFormat getTestReportFormat() {
		String format = getEngineDefaultsElementValue(SectionElementType.TEST_REPORT_FORMAT);
		if ( format == null )
		{
			return OutputFileFormat.NOT_DEFINED;
		}
		format = format.trim();
		return ConfigUtil.getOutputFileFormatByName(format);		
	}

	/**
	 * Sets <code>TestReportFormat</code> element value
	 * @param value new <code>TestReportFormat</code> element value
	 */
	public void setTestReportFormat( OutputFileFormat value ) {
		String valueStr = ConfigUtil.getOutputFileFormatName( value );
		setEngineDefaultsElementValue(SectionElementType.TEST_REPORT_FORMAT, valueStr );
	}			
	
	/**
	 * Gets <code>TestReportOutput</code> element value
	 * @return <code>TestReportOutput</code> element value
	 */
	public OutputType getTestReportOutput() {
		String output = getEngineDefaultsElementValue(SectionElementType.TEST_REPORT_OUTPUT);
		if ( output == null )
		{
			return OutputType.NOT_DEFINED;
		}
		output = output.trim();
		return ConfigUtil.getOutputTypeByName(output);		
	}

	/**
	 * Sets <code>TestReportOutput</code> element value
	 * @param value new <code>TestReportOutput</code> element value
	 */
	public void setTestReportOutput( OutputType value ) {
		String valueStr = ConfigUtil.getOutputTypeName( value );
		setEngineDefaultsElementValue(SectionElementType.TEST_REPORT_OUTPUT, valueStr );
	}		
	
	
	/**
	 * Gets <code>TestReportFileCreationMode</code> element value
	 * @return <code>TestReportFileCreationMode</code> element value
	 */
	public FileCreationMode getTestReportFileCreationMode() {
		String mode = getEngineDefaultsElementValue(SectionElementType.TEST_REPORT_CREATION_MODE);
		if ( mode == null )
		{
			return FileCreationMode.NOT_DEFINED;
		}
		mode = mode.trim();
		return ConfigUtil.getFileCreationModeByName(mode);		
	}

	/**
	 * Sets <code>TestReportFileCreationMode</code> element value
	 * @param value new <code>TestReportFileCreationMode</code> element value
	 */
	public void setTestReportFileCreationMode( FileCreationMode value ) {
		String valueStr = ConfigUtil.getFileCreationModeName( value );
		setEngineDefaultsElementValue(SectionElementType.TEST_REPORT_CREATION_MODE, valueStr );
	}		
	
	/**
	 * Gets <code>DeviceResetDllName</code> element value
	 * @return <code>DeviceResetDllName</code> element value
	 */
	public String getDeviceResetDllName() { 
		return getEngineDefaultsElementValue(SectionElementType.DEVICE_RESET_DLL_NAME);
	}

	/**
	 * Sets <code>DeviceResetDllName</code> element value
	 * @param value new <code>DeviceResetDllName</code> element value
	 */
	public void setDeviceResetDllName( String value ) { 
		setEngineDefaultsElementValue(SectionElementType.DEVICE_RESET_DLL_NAME, value);
	}
		
	/**
	 * Gets <code>Timeout</code> element value
	 * @return <code>Timeout</code> element value
	 */
	public Integer getTimeout() {
		String timeout = getEngineDefaultsElementValue(SectionElementType.TIMEOUT);
		if ( timeout == null )
		{
			return null;
		}
		timeout = timeout.trim();
		return new Integer(timeout);		
	}

	/**
	 * Sets <code>Timeout</code> element value
	 * @param value new <code>Timeout</code> element value
	 */
	public void setTimeout( Integer value ) {
		setEngineDefaultsElementValue(SectionElementType.TIMEOUT, value.toString() );
	}	
	
	/**
	 * Gets <code>UITestingSupport</code> element value
	 * @return <code>UITestingSupport</code> element value
	 */
	public YesNo getUITestingSupport() {
		return ConfigUtil.getYesNoValueByName(getEngineDefaultsElementValue(SectionElementType.UI_TESTING_SUPPORT));
	}
	
	/**
	 * Sets <code>UITestingSupport</code> element value
	 * @param value new <code>UITestingSupport</code> element value
	 */
	public void setUITestingSupport( YesNo value ){
		setEngineDefaultsElementValue(SectionElementType.UI_TESTING_SUPPORT, value.toString());
	}
	
	/**
	 * Gets <code>SeparateProcesses</code> element value
	 * @return <code>SeparateProcesses</code> element value
	 */
	public YesNo getSeparateProcesses() {
		return ConfigUtil.getYesNoValueByName(getEngineDefaultsElementValue(SectionElementType.SEPARATE_PROCESSES));
	}
	
	/**
	 * Sets <code>UITestingSupport</code> element value
	 * @param value new <code>UITestingSupport</code> element value
	 */
	public void setSeparateProcesses(YesNo value ){
		setEngineDefaultsElementValue(SectionElementType.SEPARATE_PROCESSES, value.toString());
	}
	
	/**
	 * Gets <code>DisableMeasurement</code> element value
	 * @return <code>DisableMeasurement</code> element value
	 */
	public MeasurementModule getDisableMeasurement() {
		String module = getEngineDefaultsElementValue(SectionElementType.DISABLE_MEASUREMENT);
		if ( module == null )
		{
			return MeasurementModule.NOT_DEFINED;
		}
		
		module = module.trim();
		return ConfigUtil.getMeasurementModuleByName( module );
	}

	/**
	 * Sets <code>DisableMeasurementModule</code> element value
	 * @param module new <code>DisableMeasurementModule</code> element value
	 */
	public void setDisableMeasurementModule( MeasurementModule module ) {
		String valueStr = ConfigUtil.getMeasurementModuleName( module );
		setEngineDefaultsElementValue( SectionElementType.DISABLE_MEASUREMENT, valueStr );
	}
	
	/**
	 * Gets <code>CreateLogDirectories</code> element value
	 * @return <code>CreateLogDirectories</code> element value
	 */
	public YesNo getCreateLogDirectories() {
		String value = getLoggerDefaultsElementValue(SectionElementType.CREATE_LOG_DIRECTORIES);
		return ConfigUtil.getYesNoValueByName(value);
	}

	/**
	 * Sets <code>CreateLogDirectories</code> element value
	 * @param value new <code>CreateLogDirectories</code> element value
	 */
	public void setCreateLogDirectories( YesNo value ) {
		String valueStr = ConfigUtil.getYesNoValueName( value );
		setLoggerDefaultsElementValue(SectionElementType.CREATE_LOG_DIRECTORIES, valueStr );
	}	
	
	/**
	 * Gets <code>ThreadIdToLogFile</code> element value
	 * @return <code>ThreadIdToLogFile</code> element value
	 */
	public YesNo getThreadIdToLogFile() {
		String value = getLoggerDefaultsElementValue(SectionElementType.THREAD_ID_TO_LOG_FILE);
		return ConfigUtil.getYesNoValueByName(value);
	}

	/**
	 * Sets <code>CreateLogDirectories</code> element value
	 * @param value new <code>CreateLogDirectories</code> element value
	 */
	public void setThreadIdToLogFile( YesNo value ) {
		String valueStr = ConfigUtil.getYesNoValueName( value );
		setLoggerDefaultsElementValue(SectionElementType.THREAD_ID_TO_LOG_FILE, valueStr );
	}			
	
	/**
	 * Gets <code>WithTimeStamp</code> element value
	 * @return <code>WithTimeStamp</code> element value
	 */
	public YesNo getWithTimeStamp() {
		String value = getLoggerDefaultsElementValue(SectionElementType.WITH_TIME_STAMP);
		return ConfigUtil.getYesNoValueByName(value);
	}

	/**
	 * Sets <code>WithTimeStamp</code> element value
	 * @param value new <code>WithTimeStamp</code> element value
	 */
	public void setWithTimeStamp( YesNo value ) {
		String valueStr = ConfigUtil.getYesNoValueName( value );
		setLoggerDefaultsElementValue(SectionElementType.WITH_TIME_STAMP, valueStr );
	}		
	
	/**
	 * Gets <code>WithLineBreak</code> element value
	 * @return <code>WithLineBreak</code> element value
	 */
	public YesNo getWithLineBreak() {
		String value = getLoggerDefaultsElementValue(SectionElementType.WITH_LINE_BREAK);
		return ConfigUtil.getYesNoValueByName(value);
	}

	/**
	 * Sets <code>WithLineBreak</code> element value
	 * @param value new <code>WithLineBreak</code> element value
	 */
	public void setWithLineBreak( YesNo value ) {
		String valueStr = ConfigUtil.getYesNoValueName( value );
		setLoggerDefaultsElementValue(SectionElementType.WITH_LINE_BREAK, valueStr );
	}	
	
	/**
	 * Gets <code>WithEventRanking</code> element value
	 * @return <code>WithEventRanking</code> element value
	 */
	public YesNo getWithEventRanking() {
		String value = getLoggerDefaultsElementValue(SectionElementType.WITH_EVENT_RANKING);
		return ConfigUtil.getYesNoValueByName(value);
	}

	/**
	 * Sets <code>WithEventRanking</code> element value
	 * @param value new <code>WithEventRanking</code> element value
	 */
	public void setWithEventRanking( YesNo value ) {
		String valueStr = ConfigUtil.getYesNoValueName( value );
		setLoggerDefaultsElementValue(SectionElementType.WITH_EVENT_RANKING, valueStr );
	}	
	
	/**
	 * Gets <code>FileUnicode</code> element value
	 * @return <code>FileUnicode</code> element value
	 */
	public YesNo getFileUnicode() {
		String value = getLoggerDefaultsElementValue(SectionElementType.LOG_FILE_UNICODE);
		return ConfigUtil.getYesNoValueByName(value);
	}

	/**
	 * Sets <code>FileUnicode</code> element value
	 * @param value new <code>FileUnicode</code> element value
	 */
	public void setFileUnicode( YesNo value ) {
		String valueStr = ConfigUtil.getYesNoValueName( value );
		setLoggerDefaultsElementValue(SectionElementType.LOG_FILE_UNICODE, valueStr );
	}
	
	public void setAddTestcaseTitle(YesNo value){
		String valueStr = ConfigUtil.getYesNoValueName(value);
		setLoggerDefaultsElementValue(SectionElementType.ADD_TESTCASE_TITLE, valueStr);
	}
	
	public YesNo getAddTestcaseTitle() {
		String value = getLoggerDefaultsElementValue(SectionElementType.ADD_TESTCASE_TITLE);
		return ConfigUtil.getYesNoValueByName(value);
	}
	
	/**
	 * Gets <code>EmulatorBasePath</code> element value
	 * @return <code>EmulatorBasePath</code> element value
	 */
	public String getEmulatorBasePath() {
		return getLoggerDefaultsElementValue(SectionElementType.EMULATOR_BASE_PATH);
	}

	/**
	 * Sets <code>EmulatorBasePath</code> element value
	 * @param value new <code>EmulatorBasePath</code> element value
	 */
	public void setEmulatorBasePath( String value ) {
		setLoggerDefaultsElementValue(SectionElementType.EMULATOR_BASE_PATH, value );
	}	

	/**
	 * Gets <code>HardwareBasePath</code> element value
	 * @return <code>HardwareBasePath</code> element value
	 */
	public String getHardwareBasePath() {
		return getLoggerDefaultsElementValue(SectionElementType.HARDWARE_BASE_PATH);
	}

	/**
	 * Sets <code>HardwareBasePath</code> element value
	 * @param value new <code>HardwareBasePath</code> element value
	 */
	public void setHardwareBasePath( String value ) {
		setLoggerDefaultsElementValue(SectionElementType.HARDWARE_BASE_PATH, value );
	}	
	
	/**
	 * Gets <code>LogFileCreationMode</code> element value
	 * @return <code>LogFileCreationMode</code> element value
	 */
	public FileCreationMode getLogFileCreationMode() {
		String mode = getLoggerDefaultsElementValue(SectionElementType.LOG_FILE_CREATION_MODE);
		if ( mode == null )
		{
			return FileCreationMode.NOT_DEFINED;
		}
		mode = mode.trim();
		return ConfigUtil.getFileCreationModeByName(mode);		
	}

	/**
	 * Sets <code>LogFileCreationMode</code> element value
	 * @param value new <code>LogFileCreationMode</code> element value
	 */
	public void setLogFileCreationMode( FileCreationMode value ) {
		String valueStr = ConfigUtil.getFileCreationModeName( value );
		setLoggerDefaultsElementValue(SectionElementType.LOG_FILE_CREATION_MODE, valueStr );
	}	
	
	/**
	 * Gets <code>EmulatorLogFormat</code> element value
	 * @return <code>EmulatorLogFormat</code> element value
	 */
	public OutputFileFormat getEmulatorLogFormat() {
		String format = getLoggerDefaultsElementValue(SectionElementType.EMULATOR_LOG_FORMAT);
		if ( format == null )
		{
			return OutputFileFormat.NOT_DEFINED;
		}
		format = format.trim();
		return ConfigUtil.getOutputFileFormatByName(format);		
	}

	/**
	 * Sets <code>EmulatorLogFormat</code> element value
	 * @param value new <code>EmulatorLogFormat</code> element value
	 */
	public void setEmulatorLogFormat( OutputFileFormat value ) {
		String valueStr = ConfigUtil.getOutputFileFormatName( value );
		setLoggerDefaultsElementValue(SectionElementType.EMULATOR_LOG_FORMAT, valueStr );
	}		
	
	/**
	 * Gets <code>EmulatorLogOutput</code> element value
	 * @return <code>EmulatorLogOutput</code> element value
	 */
	public OutputType getEmulatorLogOutput() {
		String output = getLoggerDefaultsElementValue(SectionElementType.EMULATOR_LOG_OUTPUT);
		if ( output == null )
		{
			return OutputType.NOT_DEFINED;
		}
		output = output.trim();
		return ConfigUtil.getOutputTypeByName(output);	
	}

	/**
	 * Sets <code>EmulatorLogOutput</code> element value
	 * @param value new <code>EmulatorLogOutput</code> element value
	 */
	public void setEmulatorLogOutput( OutputType value ) {
		String valueStr = ConfigUtil.getOutputTypeName( value );
		setLoggerDefaultsElementValue(SectionElementType.EMULATOR_LOG_OUTPUT, valueStr );
	}		
	
	/**
	 * Gets <code>HardwareLogFormat</code> element value
	 * @return <code>HardwareLogFormat</code> element value
	 */
	public OutputFileFormat getHardwareLogFormat() {
		String format = getLoggerDefaultsElementValue(SectionElementType.HARDWARE_LOG_FORMAT);
		if ( format == null )
		{
			return OutputFileFormat.NOT_DEFINED;
		}
		format = format.trim();
		return ConfigUtil.getOutputFileFormatByName(format);		
	}

	/**
	 * Sets <code>HardwareLogFormat</code> element value
	 * @param value new <code>HardwareLogFormat</code> element value
	 */
	public void setHardwareLogFormat( OutputFileFormat value ) {
		String valueStr = ConfigUtil.getOutputFileFormatName( value );
		setLoggerDefaultsElementValue(SectionElementType.HARDWARE_LOG_FORMAT, valueStr );
	}			
	
	/**
	 * Gets <code>HardwareLogOutput</code> element value
	 * @return <code>HardwareLogOutput</code> element value
	 */
	public OutputType getHardwareLogOutput() {
		String output = getLoggerDefaultsElementValue(SectionElementType.HARDWARE_LOG_OUTPUT);
		if ( output == null )
		{
			return OutputType.NOT_DEFINED;
		}
		output = output.trim();
		return ConfigUtil.getOutputTypeByName(output);	
	}

	/**
	 * Sets <code>HardwareLogOutput</code> element value
	 * @param value new <code>HardwareLogOutput</code> element value
	 */
	public void setHardwareLogOutput( OutputType value ) {
		String valueStr = ConfigUtil.getOutputTypeName( value );
		setLoggerDefaultsElementValue(SectionElementType.HARDWARE_LOG_OUTPUT, valueStr );
	}		
	
	/**
	 * Gets configuration source
	 * @return configuration source
	 */
	public String getConfigSource() {
		return configSource;
	}

	/**
	 * Adds new module to configuration source
	 * @param name module name
	 * @return added module index
	 */
	public int addModule( String name ) {
		int moduleSectionPos = -1;
		if ( modules != null ) {
			if ( modules.size() != 0 ) {
				ModuleSection module = modules.get(modules.size()-1);  
				if ( module.endLine != -1 ) {
					moduleSectionPos = module.endLine + 1;
				} else {
					int lastLine = module.beginLine;
					if ( module.name.lineNumber != -1 ) {
						lastLine = module.name.lineNumber;
					}
					if ( ( module.iniFile != null ) && 
							( module.iniFile.lineNumber > lastLine ) ) {
						lastLine = module.iniFile.lineNumber;
					}
					if ( module.testCaseFiles != null ) {
						for( int i = 0; i < module.testCaseFiles.size(); i++ ) {
							if ( module.testCaseFiles.get(i).lineNumber > lastLine ) {
								lastLine = module.testCaseFiles.get(i).lineNumber;
							}
						}
					}
					moduleSectionPos = lastLine + 1; 
				}
			}
		}
		if ( moduleSectionPos == -1 ) {
			if ( engineDefaults.endLine != -1 ) {
				moduleSectionPos = engineDefaults.endLine + 1; 
			} else {
				moduleSectionPos = 1;
			}
		}
		
		String moduleNameTag = ConfigUtil.getModuleSectionElementTag(SectionElementType.MODULE_NAME); 
		createSection( moduleSectionPos, MODULE_SECTION_BEGIN_TAG, MODULE_SECTION_END_TAG );
		createElement( moduleSectionPos + 2, moduleNameTag, name );
		parseConfig( configSource );
		return modules.size() - 1;
	}

	/**
	 * Removes module from configuration source
	 * @param moduleIndex module index
	 */
	public void removeModule( int moduleIndex ) {
		if ( ( modules == null ) || ( moduleIndex < 0 ) || ( modules.size() < moduleIndex ) ) {
			return;
		}
		ModuleSection moduleSection = modules.get(moduleIndex);
		if ( ( moduleSection == null ) || (moduleSection.beginLine < 1 ) ||
				( moduleSection.endLine < 1 ) || ( moduleSection.endLine < moduleSection.beginLine ) ) {
			return;
		}
		removeBlock(moduleSection.beginLine, moduleSection.endLine);
		parseConfig( configSource );
	}

	/**
	 * Updates module name value
	 * @param moduleIndex module index
	 * @param newName new module name
	 */
	public void updateModule( int moduleIndex, String newName ) {
		if ( ( modules == null ) || ( moduleIndex < 0 ) || ( modules.size() < moduleIndex ) ) {
			return;
		}
		ModuleSection moduleSection = modules.get(moduleIndex);
		if ( ( moduleSection == null ) || (moduleSection.name == null ) ||
				( moduleSection.name.lineNumber < 1 ) ) {
			return;
		}

		String tag = ConfigUtil.getModuleSectionElementTag(SectionElementType.MODULE_NAME);
		updateElement(moduleSection.name.lineNumber, tag, newName );
		parseConfig( configSource );
	}	
	
	/**
	 * Adds new ini file to module
	 * @param moduleIndex module index
	 * @param iniFilePath ini file path
	 */
	public void addIniFileToModule( int moduleIndex, String iniFilePath ) {
		if ( ( modules == null ) || ( moduleIndex < 0 ) || ( modules.size() < moduleIndex ) ) {
			return;
		}
		ModuleSection moduleSection = modules.get(moduleIndex);
		if ( moduleSection.iniFile != null ) {
			return;
		}
		int lineNumber = moduleSection.beginLine;
		if ( ( moduleSection.name != null ) && ( moduleSection.name.lineNumber > 0 ) ) {
			lineNumber = moduleSection.name.lineNumber;
		}
		lineNumber++;
		
		String tag = ConfigUtil.getModuleSectionElementTag(SectionElementType.INI_FILE);
		createElement( lineNumber, tag, iniFilePath );
		parseConfig( configSource );
	}
	
	/**
	 * Removes ini file from module
	 * @param moduleIndex module index
	 */
	public void removeIniFileFromModule( int moduleIndex ) {
		if ( ( modules == null ) || ( moduleIndex < 0 ) || ( modules.size() < moduleIndex ) ) {
			return;
		}
		ModuleSection moduleSection = modules.get(moduleIndex);
		if ( ( moduleSection == null ) || ( moduleSection.iniFile == null ) || 
				(moduleSection.iniFile.lineNumber < 1 ) ) {
			return;
		}
		removeBlock(moduleSection.iniFile.lineNumber, moduleSection.iniFile.lineNumber);		
		parseConfig( configSource );
	}	

	/**
	 * Updates module ini file path value
	 * @param moduleIndex module index
	 * @param iniFilePath new ini file path
	 */
	public void updateIniFileInModule( int moduleIndex, String iniFilePath ) {
		if ( ( modules == null ) || ( moduleIndex < 0 ) || ( modules.size() < moduleIndex ) ) {
			return;
		}
		ModuleSection moduleSection = modules.get(moduleIndex);
		if ( ( moduleSection == null ) || ( moduleSection.iniFile == null ) || 
				(moduleSection.iniFile.lineNumber < 1 ) ) {
			return;
		}

		String tag = ConfigUtil.getModuleSectionElementTag(SectionElementType.INI_FILE);
		updateElement(moduleSection.iniFile.lineNumber, tag, iniFilePath );
		parseConfig( configSource );
	}
	
	/**
	 * Adds new testcase file path to module
	 * @param moduleIndex module index
	 * @param testcaseFilePath testcase file path
	 */
	public void addTestCaseFileToModule( int moduleIndex, String testcaseFilePath ) {
		if ( ( modules == null ) || ( moduleIndex < 0 ) || ( modules.size() < moduleIndex ) ) {
			return;
		}
		ModuleSection moduleSection = modules.get(moduleIndex);

		int lineNumber = moduleSection.beginLine;
		if ( ( moduleSection.name != null ) && ( moduleSection.name.lineNumber > 0 ) ) {
			lineNumber = moduleSection.name.lineNumber;
		}
		if ( ( moduleSection.iniFile != null ) && ( moduleSection.iniFile.lineNumber > 0 ) ) {
			lineNumber = moduleSection.iniFile.lineNumber;
		}
		for ( int i = 0; i < moduleSection.testCaseFiles.size(); i++ ) {
			if ( lineNumber < moduleSection.testCaseFiles.get(i).lineNumber ) {
				lineNumber = moduleSection.testCaseFiles.get(i).lineNumber;
			}
		}		
		lineNumber++;
		
		String tag = ConfigUtil.getModuleSectionElementTag(SectionElementType.TEST_CASE_FILE);
		createElement( lineNumber, tag, testcaseFilePath );
		parseConfig( configSource );
	}
	
	/**
	 * Remove testcase file from module
	 * @param moduleIndex module index
	 * @param configFileIndex testcase file path index
	 */
	public void removeTestCaseFileFromModule( int moduleIndex, int configFileIndex ) {
		if ( ( modules == null ) || ( moduleIndex < 0 ) || ( modules.size() < moduleIndex ) ) {
			return;
		}		
		ModuleSection moduleSection = modules.get(moduleIndex);
		if ( ( moduleSection == null ) || ( moduleSection.testCaseFiles == null ) || 
				(moduleSection.testCaseFiles.size() < configFileIndex ) ) {
			return;
		}
		
		SectionElement configFile = moduleSection.testCaseFiles.get(configFileIndex);
		if ( ( configFile == null ) || ( configFile.lineNumber < 1 ) ) {
			return;
		}
		
		removeBlock(configFile.lineNumber, configFile.lineNumber);		
		parseConfig( configSource );
	}	

	/**
	 * Updates testcase file path value
	 * @param moduleIndex module index
	 * @param testcaseFileIndex testcase file path index
	 * @param testcaseFilePath new testcase file path
	 */
	public void updateTestCaseFileInModule( int moduleIndex, int testcaseFileIndex, String testcaseFilePath ) {
		if ( ( modules == null ) || ( moduleIndex < 0 ) || ( modules.size() < moduleIndex ) ) {
			return;
		}		
		ModuleSection moduleSection = modules.get(moduleIndex);
		if ( ( moduleSection == null ) || ( moduleSection.testCaseFiles == null ) || 
				(moduleSection.testCaseFiles.size() < testcaseFileIndex ) ) {
			return;
		}
		
		SectionElement configFile = moduleSection.testCaseFiles.get( testcaseFileIndex );
		if ( ( configFile == null ) || ( configFile.lineNumber < 1 ) ) {
			return;
		}
		
		String tag = ConfigUtil.getModuleSectionElementTag(SectionElementType.TEST_CASE_FILE);
		updateElement( configFile.lineNumber, tag, testcaseFilePath );
		parseConfig( configSource );
	}	
		
	/**
	 * Removes block of text from configuration source
	 * @param startLineNumber block begine line
	 * @param endLineNumber block end line
	 */
	private void removeBlock( int startLineNumber, int endLineNumber ) {
		BufferedReader configSourceReader = new BufferedReader(new StringReader(configSource));
		StringWriter newConfigSourceWriter = new StringWriter();
		BufferedWriter buf = new BufferedWriter( newConfigSourceWriter );
		
		try {
			int currentLineNumber = 0;
			while( true ) {
				currentLineNumber++;
				String line = configSourceReader.readLine();
				if ( line == null ) {
					break;
				}
				if ( ( currentLineNumber >= startLineNumber ) && 
						( currentLineNumber <= endLineNumber ) ) {
					continue;
				}
								
				buf.write(line);
				buf.newLine();
			}
			
			// Flush data to config writer
			buf.flush();
			// replace old config by new one
			configSource = newConfigSourceWriter.getBuffer().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		parseConfig(configSource);		
	}
	
	/**
	 * Create new section in configuration source
	 * @param lineNumber section position 
	 * @param sectionBeginTag section begine tag
	 * @param sectionEndTag section end tag
	 */
	private void createSection( int lineNumber, String sectionBeginTag, String sectionEndTag ) {
		BufferedReader configSourceReader = new BufferedReader(new StringReader(configSource));
		StringWriter newConfigSourceWriter = new StringWriter();
		BufferedWriter buf = new BufferedWriter( newConfigSourceWriter );
		
		try {
			int currentLineNumber = 0;
			boolean added = false;
			boolean eof = false;
			while( true ) {
				currentLineNumber++;
				String line = configSourceReader.readLine();
				if ( line == null ) {
					// End of config source
					eof = true;
				}
				
				if ( ( currentLineNumber == lineNumber ) || 
						( ( !added ) && ( eof == true ) ) ) {
					buf.newLine();
					buf.write(sectionBeginTag);
					buf.newLine();
					buf.write(sectionEndTag);
					buf.newLine();
					added = true;
				}
				if ( eof == true ) {
					break;
				}
				
				// write line to new config source buffer 
				buf.write(line);
				buf.newLine();
			}
			
			// Flush data to config writer
			buf.flush();
			// replace old config by new one
			configSource = newConfigSourceWriter.getBuffer().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		parseConfig(configSource);
	}

}
