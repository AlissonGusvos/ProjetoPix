// Função para ler parâmetros da URL
function getParam(nome) {
    const url = new URL(window.location.href);
    return url.searchParams.get(nome);
}

// Modal erro já enviado anteriormente
function mostrarModal(mensagem) {
    const modal = document.getElementById("modalErro");
    const mensagemErro = document.getElementById("mensagemErro");
    const btnFecharModal = document.getElementById("btnFecharModal");
    const fecharModalX = document.getElementById("fecharModal");

    mensagemErro.textContent = mensagem;
    modal.style.display = "block";

    fecharModalX.onclick = () => modal.style.display = "none";
    btnFecharModal.onclick = () => modal.style.display = "none";
    window.onclick = (event) => { if (event.target === modal) modal.style.display = "none"; };
}

// Função para mostrar modal de confirmação customizado
function mostrarConfirmacao(mensagem, callbackSim, callbackNao) {
    const modal = document.getElementById("modalConfirmacao");
    const mensagemConfirmacao = document.getElementById("mensagemConfirmacao");
    const btnSim = document.getElementById("btnSim");
    const btnNao = document.getElementById("btnNao");

    mensagemConfirmacao.textContent = mensagem;
    modal.style.display = "block";

    btnSim.onclick = () => {
        modal.style.display = "none";
        callbackSim();
    };
    btnNao.onclick = () => {
        modal.style.display = "none";
        callbackNao();
    };

    // Fechar modal clicando fora
    window.onclick = (event) => {
        if (event.target === modal) {
            modal.style.display = "none";
            callbackNao();
        }
    };
}

const erro = getParam("erro");

if (erro === "saldoInsuficiente") {
    mostrarModal("Erro: Saldo insuficiente.");
} else if (erro === "limiteExcedido") {
    mostrarModal("Erro: Valor ultrapassa o limite de transferência.");
} else if (erro === "valoresSemelhantes") {
    const chave = getParam("chave");
    const valor = getParam("valor");

    mostrarConfirmacao(
        "Transferência suspeita detectada. Deseja continuar mesmo assim?",
        () => {
            // Sim: criar formulário e reenviar com confirmar=true
            const form = document.createElement("form");
            form.method = "POST";
            form.action = "/pix";

            const inputChave = document.createElement("input");
            inputChave.type = "hidden";
            inputChave.name = "chave";
            inputChave.value = chave;

            const inputValor = document.createElement("input");
            inputValor.type = "hidden";
            inputValor.name = "valor";
            inputValor.value = valor;

            const inputConfirmar = document.createElement("input");
            inputConfirmar.type = "hidden";
            inputConfirmar.name = "confirmar";
            inputConfirmar.value = "true";

            form.appendChild(inputChave);
            form.appendChild(inputValor);
            form.appendChild(inputConfirmar);
            document.body.appendChild(form);
            form.submit();
        },
        () => {
            // Não: mostrar modal informando cancelamento
            mostrarModal("Transferência cancelada.");
        }
    );

} else if (erro === "contaNaoEncontrada") {
    mostrarModal("Erro: Conta não encontrada. Verifique a chave PIX e tente novamente.");
}
