package br.com.demoapi.service;

import br.com.demoapi.entity.Produto;
import br.com.demoapi.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }
    public void delete(Long id) {
        try {
            produtoRepository.deleteById(id);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException("Erro ao excluir o produto");
        }
    }
    public Optional<Produto> buscarPorId(Long id) {
        try {
            return produtoRepository.findById(id);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException("Erro ao buscar o produto");
        }
    }

}
