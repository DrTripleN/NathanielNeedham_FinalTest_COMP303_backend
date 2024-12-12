package com.example.comp303testfinal_needham.Controller;

import com.example.comp303testfinal_needham.Entity.Banks;
import com.example.comp303testfinal_needham.Repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/banks")
@CrossOrigin(origins = "http://localhost:5173")// Allow requests from frontend
public class BankController {

    @Autowired
    private BankRepository bankRepository;

    //Adding a Bank
    @PostMapping("/add")
    public ResponseEntity<Banks> addBank(@RequestBody Banks bank) {
        Banks newBank = bankRepository.save(bank);
        return ResponseEntity.ok(newBank);
    }

    //Get all Banks
    @GetMapping("/all")
    public ResponseEntity<List<Banks>> getAllBanks() {
        List<Banks> banks = bankRepository.findAll();
        return ResponseEntity.ok(banks);
    }

    //Get bank by ID
    @GetMapping("/id/{id}")
    public ResponseEntity<Banks> getBankById(@PathVariable String id) {
        Optional<Banks> bank = bankRepository.findById(id);
        return bank.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Get bank by bankname
    @GetMapping("/name/{bankName}")
    public ResponseEntity<Banks> getBankByBankName(@PathVariable String bankName) {
        List<Banks> banks = bankRepository.findByBankName(bankName);
        return banks.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(banks.get(0));
    }

    //Delete a Bank by its ID
    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<String> deleteBankById(@PathVariable String id) {
        return bankRepository.findById(id).map(banks -> {
            bankRepository.deleteById(id);
            return ResponseEntity.ok("Bank deleted successfully.");
        }).orElseGet(() -> ResponseEntity.status(404).body("Bank not found."));


    }
    @DeleteMapping("/delete/name/{bankName}")
    public ResponseEntity<String> deleteBankByBankName(@PathVariable String bankName) {
        // Find banks by name
        List<Banks> banks = bankRepository.findByBankName(bankName);

        // Check if the list is empty (no banks found)
        if (banks.isEmpty()) {
            return ResponseEntity.status(404).body("Bank not found.");
        } else {
            // Delete all the banks with the specified name
            bankRepository.deleteAll(banks);
            return ResponseEntity.ok(bankName + " deleted successfully.");
        }
    }

    //Update Bank Info through ID
    @PutMapping("/edit/id/{id}")
    public ResponseEntity<Banks> updateBankById(@PathVariable String id, @RequestBody Banks updatedBank) {
        return bankRepository.findById(id).map(existingBank -> {
            existingBank.setBankName(updatedBank.getBankName());
            existingBank.setBankYear(updatedBank.getBankYear());
            existingBank.setBankEmp(updatedBank.getBankEmp());
            existingBank.setBankAddress(updatedBank.getBankAddress());
            existingBank.setBankBranches(updatedBank.getBankBranches());
            existingBank.setBankATMs(updatedBank.getBankATMs());

            Banks savedBank = bankRepository.save(existingBank);
            return ResponseEntity.ok(savedBank);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Update Bank Info through bankName
    @PutMapping("edit/name/{bankName}")
    public ResponseEntity<List<Banks>> updateBankByBankName(@PathVariable String bankName, @RequestBody Banks updatedBank) {
        List<Banks> banks = bankRepository.findByBankName(bankName);

        if (banks.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<Banks> updatedBanks = banks.stream().map(existingBank -> {
                existingBank.setBankName(updatedBank.getBankName());
                existingBank.setBankYear(updatedBank.getBankYear());
                existingBank.setBankEmp(updatedBank.getBankEmp());
                existingBank.setBankAddress(updatedBank.getBankAddress());
                existingBank.setBankBranches(updatedBank.getBankBranches());
                existingBank.setBankATMs(updatedBank.getBankATMs());

                return bankRepository.save(existingBank);
            }).collect(Collectors.toList());

            return ResponseEntity.ok(updatedBanks);
        }
    }

}
