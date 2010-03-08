/*
* Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies).
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



package com.symbian.driver.remoting.master;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Represents a snapshotable object - an object that the application wants to
 * have a snapshot of (i.e. a copy of the state of the object at a certain time
 * of its life, so that it can later be restored back to the state taken by the
 * snapshot).
 * 
 * 
 * @author EngineeringTools
 */
public abstract class Snapshotable {

	private static final String SERIALIZE_FOLDER = "." + File.separator + "store";

	/**
	 * Take a snapshot of the current state of the object.
	 */
	public abstract void takeSnapshot();

	/**
	 * Restore the object to the state defined by the snapshot.
	 */
	public abstract void restoreSnapshot();

	/**
	 * Find out whether the snapshotable object needs to be restored.
	 * 
	 * @return <code>true</code> if the object needs to be restored, <code>false</code> otherwise.
	 */
	public abstract boolean needRestore();

	/**
	 * Serialize the snapshot to the specified file. Subclasse may override this
	 * and provide their own way of storing, if they don't want to use this.
	 * 
	 * @param aSnapshot
	 *            Snapshot object
	 * @param aSerializeFile
	 *            String a file path
	 * @throws IOException 
	 */
	protected void serialize(Snapshot aSnapshot, String aSerializeFile) throws IOException {

		ObjectOutputStream out = null;

		try {
			File serializePathName = new File(new File(SERIALIZE_FOLDER), aSerializeFile);
			out = new ObjectOutputStream(new FileOutputStream(serializePathName));
			out.writeObject(aSnapshot);
		} catch (IOException ioe) {
			throw new IOException("Problem while serializing to " + aSerializeFile + ": " + ioe.getMessage());
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException ioe) {
				throw new IOException(ioe.getMessage());
			}
		}
	}

	/**
	 * Deserialize the snapshot contained in the specified file. May be
	 * overridden by subclasses.
	 * 
	 * @param aDeserializeFile
	 *            String : file path
	 * @return The snapshot of the deserialised object.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	protected Snapshot deserialize(String aDeserializeFile) throws IOException, ClassNotFoundException {

		ObjectInputStream in = null;
		Snapshot snapshot = null;

		try {
			File deserializePathName = new File(new File(SERIALIZE_FOLDER), aDeserializeFile);
			in = new ObjectInputStream(new FileInputStream(deserializePathName));
			snapshot = (Snapshot) in.readObject();
		} catch (IOException ioe) {
			throw new IOException("Problem while deserializing from " + aDeserializeFile + ": " + ioe.getMessage());
		} catch (ClassNotFoundException cne) {
			throw new ClassNotFoundException(cne.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ioe) {
				throw new IOException(ioe.getMessage());
			}
		}
		return snapshot;
	}
}
