package br.com.demoapi.controller;

import br.com.demoapi.entity.Produto;
import br.com.demoapi.repository.ProdutoRepository;
import br.com.demoapi.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoService produtoService,
                             ProdutoRepository produtoRepository) {
        this.produtoService = produtoService;
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> buscarTodos() {
        return ResponseEntity.ok(produtoService.buscarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Produto>> buscar(@PathVariable Long id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }
    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
        var novoProduto = produtoService.salvar(produto);
        return new ResponseEntity<>(novoProduto, null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produto.setId(id);
        Produto produtoatualizado = produtoService.salvar(produto);
        return ResponseEntity.ok(produtoatualizado);
    }
}
