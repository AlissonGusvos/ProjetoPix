
    // Função para ler parâmetros da URL
    function getParam(nome) {
        const url = new URL(window.location.href);
        return url.searchParams.get(nome);
    }

    const erro = getParam("erro");

    if (erro === "saldoInsuficiente") {
        alert("Erro: Saldo insuficiente.");
    } else if (erro === "limiteExcedido") {
        alert("Erro: Valor ultrapassa o limite de transferência.");
    } else if (erro === "valoresSemelhantes") {
        const chave = getParam("chave");
        const valor = getParam("valor");

        if (confirm("Transferência suspeita detectada. Deseja continuar mesmo assim?")) {
            // Criar formulário e reenviar com 'confirmar=true'
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
        } else {
            alert("Transferência cancelada.");
        }
    }
