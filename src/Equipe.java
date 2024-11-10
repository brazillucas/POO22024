public class Equipe {
	private String nome;
	private int pontos;
	private int golsPro;
	private int saldoGols;
	private int cartVermelho;
	private int cartAmarelo;

	public Equipe (String nome) {
		this.nome = nome;
		this.pontos = 0;
		this.saldoGols = 0;
		this.cartAmarelo = 0;
		this.cartVermelho = 0;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPontos() {
		return this.pontos;
	}

	public void setPontos(int pontos) {
		this.pontos += pontos;
	}

	public int getGolsPro() {
		return this.golsPro;
	}

	public void setGolsPro(int golsPro) {
		this.golsPro = golsPro;
	}

	public int getSaldoGols() {
		return this.saldoGols;
	}

	public void setSaldoGols(int saldoGols) {
		this.saldoGols += saldoGols;
	}

	public int getCartVermelho() {
		return this.cartVermelho;
	}

	public void setCartVermelho(int cartVermelho) {
		this.cartVermelho += cartVermelho;
	}

	public int getCartAmarelo() {
		return this.cartAmarelo;
	}

	public void setCartAmarelo(int cartAmarelo) {
		this.cartAmarelo += cartAmarelo;
	}

}
