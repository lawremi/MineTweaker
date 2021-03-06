/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class GrinderAddBlacklistAction implements IUndoableAction {
	private final Class<?> entityClass;
	private final boolean alreadyBlacklisted;
	
	public GrinderAddBlacklistAction(Class<?> entityClass) {
		this.entityClass = entityClass;
		alreadyBlacklisted = MFRHacks.grindableBlacklist == null ? false : MFRHacks.grindableBlacklist.contains(entityClass);
	}

	public void apply() {
		if (!alreadyBlacklisted) {
			FactoryRegistry.registerGrinderBlacklist(entityClass);
		}
	}

	public boolean canUndo() {
		return MFRHacks.grindableBlacklist != null;
	}

	public void undo() {
		if (!alreadyBlacklisted) {
			MFRHacks.grindableBlacklist.remove(entityClass);
		}
	}

	public String describe() {
		return "Blacklisting grindable " + entityClass.getCanonicalName();
	}

	public String describeUndo() {
		return "Un-blacklisting grindable " + entityClass.getCanonicalName();
	}
}
