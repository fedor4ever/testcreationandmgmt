@rem
@rem Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies).
@rem All rights reserved.
@rem This component and the accompanying materials are made available
@rem under the terms of "Eclipse Public License v1.0"
@rem which accompanies this distribution, and is available
@rem at the URL "http://www.eclipse.org/legal/epl-v10.html".
@rem
@rem Initial Contributors:
@rem Nokia Corporation - initial contribution.
@rem
@rem Contributors:
@rem
@rem Description: 
@rem

@echo OFF
set INST_DIR=%~dp0
if /i "%1" == "master" goto next
@ java -Xmx512m -Xrs -Djava.util.logging.config.file="%INST_DIR%/eclipse/logging.properties" -Djava.rmi.server.codebase="file:/%INST_DIR%/tdplugins/eclipse/plugins/com.symbian.driver.core_2.3.0.jar" -jar "%INST_DIR%/eclipse/startup.jar" -application com.symbian.driver.core.TestDriver %*
goto end
:next
@ java -Xmx512m -Xrs -Djava.util.logging.config.file="%INST_DIR%/eclipse/logging.properties" -Djava.rmi.server.codebase="file:/%INST_DIR%/tdplugins/eclipse/plugins/com.symbian.driver.core_2.3.0.jar" -jar "%INST_DIR%/eclipse/startup.jar" -noExit -application com.symbian.driver.core.TestDriver %*
:end
