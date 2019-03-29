package project.exception;

public class LottoNonDisponibileException extends Exception{
	
	private static final long serialVersionUID = 1L;
	public LottoNonDisponibileException(){}
	public LottoNonDisponibileException(String str){
		super(str);
	}
}
