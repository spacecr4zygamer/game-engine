package gameenginepack;

public abstract class Instance {
	
	public Instance Parent;
	

	public abstract void setPropertyChild(String PropertyName, String Property);
	public void setProperty(String PropertyName, String Property) {
		switch(PropertyName) {
		
		default:
			setPropertyChild(PropertyName,Property);
		}
	}
	
	//public abstract void setvar(String var, Object value);
	/* Ver�ndert Variablen
	    *  String var = Variablen Name
	    *  Object value = Neuer Wert der Variable
	    * 
	    public void setvar(String var, Object value) {
	        try {
	            // Variable abrufen
	            Field f1 = this.getClass().getField(var);
	            // Variable ver�ndern
	            f1.set(this, value);
	        } catch (NoSuchFieldException | IllegalAccessException e) {
	            e.printStackTrace();
	        }
	    }

	    public Object getvar(String var) {
	        try {
	            // Variable abrufen
	            Field f1 = this.getClass().getField(var);
	            // Wert zur�ck geben
	            return f1.get(this);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	}*/
	
}
