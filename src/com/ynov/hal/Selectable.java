package com.ynov.hal;

// Une INTERFACE ressemble � une classe mais ne contient que des m�thodes
// sans code (m�thodes abstraites)
public interface Selectable {
	public void select();
	public void unselect();
	public boolean isSelected();
}
