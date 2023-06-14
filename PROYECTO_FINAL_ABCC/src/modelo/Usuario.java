package modelo;

public class Usuario {
	private String Usuario;
	private String Contraseña;
	private Byte NoUsuario;

	public Usuario(Byte noUsuario,String usuario, String contraseña) {
		Usuario = usuario;
		Contraseña = contraseña;
		NoUsuario = noUsuario;
	}

	public String getNombre() {
		return Usuario;
	}
	public void setNombre(String Usuario) {
		this.Usuario = Usuario;
	}


	public String getContraseña() {
		return Contraseña;
	}
	public void setContraseña(String contraseña) {
		this.Contraseña = contraseña;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public Byte getNoUsuario() {
		return NoUsuario;
	}

	public void setNoUsuario(Byte noUsuario) {
		NoUsuario = noUsuario;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
