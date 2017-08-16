package states;

import org.uqbar.commons.utils.Observable;

@Observable
public class EstadoCrearTaxativos implements EstadoCrearCriterios {

	private Boolean timeForOperations = false;
	private Boolean timeForIndicators = true;
	private Boolean timeForModificators = true;
	private Boolean timeForConstant = false;
	private Boolean timeForSave = false;
	
	@Override
	public void itsTimeForOperations() {
		this.timeForOperations = true;
		this.timeForIndicators = false;
		this.timeForModificators = false;
	}

	@Override
	public void itsTimeForIndicators() {
		this.timeForOperations = false;
		this.timeForIndicators = true;
		this.timeForModificators = true;
		this.timeForConstant = false;
		this.timeForSave = false;
	}

	@Override
	public void itsTimeForSave() {
		this.timeForOperations = false;
		this.timeForIndicators = false;
		this.timeForConstant = false;
		this.timeForSave = true;
	}
	
	public void disableModifiers() {
		this.timeForModificators = false;
	}
	
	public void itsTimeForSomeConstant() {
		this.timeForConstant = true;
	}

	public Boolean getTimeForOperations() {
		return timeForOperations;
	}

	public Boolean getTimeForIndicators() {
		return timeForIndicators;
	}

	public Boolean getTimeForModificators() {
		return timeForModificators;
	}

	public Boolean getTimeForConstant() {
		return timeForConstant;
	}

	public Boolean getTimeForSave() {
		return timeForSave;
	}
}