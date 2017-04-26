package ui.windows;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.PasswordField;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import viewModels.LoginViewModel;

@SuppressWarnings("serial")
public class LoginWindow extends SimpleWindow<LoginViewModel> {
	
	public LoginWindow(WindowOwner parent) {
		super(parent, new LoginViewModel());
	}
	
	@Override
	protected void addActions(Panel panelActions) {	//se lo deja vacio. Si lo uso, pierdo el layout
	}

	protected void createFormPanel(Panel formPanel){
		this.setTitle("Consulta de inversiones");
		formPanel.setLayout(new VerticalLayout());
		
		new Label(formPanel)
		.setText("Hola Hector");
		
		new Label(formPanel)
		.setText("Ingresar su contraseña");
		
		new PasswordField(formPanel)
		.bindValueToProperty("password");
		
		new Button(formPanel)
		.setCaption("Log In")
		.onClick(()-> this.atenderLogin(this.getModelObject().getPassword()));
	}
	
	private void atenderLogin(String password){
		if(this.getModelObject().puedeLoguearse()) {
			this.close();
			this.abrirMenuPrincipal();
		} else {
			this.abrirVentanaDeErorr();
		}
	}
	
	private void abrirMenuPrincipal(){
		SimpleWindow<?> ventanaMenuPrincipal= new MenuPrincipalWindow(this);
		ventanaMenuPrincipal.open();
	}
	
	private void abrirVentanaDeErorr(){
		SimpleWindow<?> ventanaError = new ErrorWindow(this);
		ventanaError.open();
	}
	
}