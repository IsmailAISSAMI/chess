package com.ynov.hal;

// Une INTERFACE ressemble à une classe mais ne contient que des méthodes
// sans code (méthodes abstraites)
public interface Selectable {
	public void select();
	public void unselect();
	public boolean isSelected();
}
