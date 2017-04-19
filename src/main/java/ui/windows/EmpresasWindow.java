package ui.windows;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import model.Empresa;
import repositories.Repositorios;
import viewModels.EmpresasViewModel;

@SuppressWarnings("serial")
public class EmpresasWindow extends SimpleWindow<EmpresasViewModel> {

	public EmpresasWindow(WindowOwner parent) {
		super(parent, new EmpresasViewModel());
	}

	@Override
	protected void addActions(Panel arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		this.setTitle("Empresas");

		Table<Empresa> tableEmpresas = new Table<Empresa>(formPanel, Empresa.class);
		tableEmpresas.bindItemsToProperty("empresas");
		tableEmpresas.bindValueToProperty("empresaSeleccionada");
		tableEmpresas.setNumberVisibleRows(this.getModelObject().cantidadEmpresas() + 1);

		Column<Empresa> columnaId = new Column<Empresa>(tableEmpresas);
		columnaId.setFixedSize(30);
		columnaId.setTitle("Id");
		columnaId.bindContentsToProperty("id");

		Column<Empresa> columnaRazonSocial = new Column<Empresa>(tableEmpresas);
		columnaRazonSocial.setFixedSize(150);
		columnaRazonSocial.setTitle("Razón Social");
		columnaRazonSocial.bindContentsToProperty("razonSocial");

		Column<Empresa> columnaNombreFantasia = new Column<Empresa>(tableEmpresas);
		columnaNombreFantasia.setFixedSize(150);
		columnaNombreFantasia.setTitle("Nombre de Fantasía");
		columnaNombreFantasia.bindContentsToProperty("nombreFantasia");

		Column<Empresa> columnaValor = new Column<Empresa>(tableEmpresas);
		columnaValor.setFixedSize(150);
		columnaValor.setTitle("CUIT");
		columnaValor.bindContentsToProperty("cuit");

		new Button(formPanel).setCaption("Ver opciones").onClick(() -> this.mostrarOpciones());
	}

	private void mostrarOpciones() {
		this.getModelObject().guardarEmpresaSeleccionadoEnRepositorio();
		// SimpleWindow<?> ventanaEvento = new OpcionesWindow(this);
		// ventanaEvento.open();
		System.out.println("abre ventana de con las opciones a realizar para la empresa seleccionada: cargar cuentas, cargar indicadores, consultar valores, etc...");
	}

}