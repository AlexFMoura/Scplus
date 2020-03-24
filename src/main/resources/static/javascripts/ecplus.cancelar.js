var Ecplus = Ecplus || {};

Ecplus.BotaoSubmit = (function() {
	
	function BotaoSubmit(){
		this.submitBtn  = $('.js-submit-btn');
		this.formulario = $('.js-formulario-principal');
	}
	
	BotaoSubmit.prototype.enable = function(){
		this.submitBtn.on('click', onSubmit.bind(this));
	}

	function onSubmit(evento){
		evento.preventDefault();
		var botaoClicado = $(evento.target);
		var acao = botaoClicado.data('acao');
		
		var acaoInput = $('<input type="hidden"/>');
		acaoInput.attr('name', acao);
		console.log(acao);
		this.formulario.append(acaoInput);
		this.formulario.submit();
		/*		var botaoClicado = $(evento.currentTarget);
		var url = botaoClicado.data('url');
		console.log(url);
		$.ajax({
			url: url,
			method: 'GET',
			success: function() {
				window.location.reload();
			}
		});*/
	}	
	
	return BotaoSubmit;
	
}());

$(function() {
	
	var botaoSubmit  = new Ecplus.BotaoSubmit();
	botaoSubmit .enable();
});