package states;

import org.uqbar.commons.utils.Observable;

@Observable
public class EstadoCrearComparativos implements EstadoCrearCriterios {

	private Boolean timeForOperations = true;
	private Boolean timeForIndicators = false;
	private Boolean timeForSave = false;
	
	@Override
	public void itsTimeForOperations() {
		this.timeForOperations = true;
		this.timeForIndicators = false;
		this.timeForSave = false;
	}

	@Override
	public void itsTimeForIndicators() {
		this.timeForOperations = false;
		this.timeForIndicators = true;
	}

	@Override
	public void itsTimeForSave() {
		this.timeForOperations = false;
		this.timeForIndicators = false;
		this.timeForSave = true;
	}

	public Boolean getTimeForOperations() {
		return timeForOperations;
	}

	public Boolean getTimeForIndicators() {
		return timeForIndicators;
	}

	public Boolean getTimeForSave() {
		return timeForSave;
	}
}