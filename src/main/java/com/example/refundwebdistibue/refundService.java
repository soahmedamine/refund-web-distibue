package com.example.refundwebdistibue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class refundService {
    @Autowired
    private refundRepo refundRepository;

    public refund addRefund(refund refund) {
        return refundRepository.save(refund);
    }

    public List<refund> getAll() {
        return refundRepository.findAll();
    }

    public refund updateRefund(int id, refund newRefund) {
        Optional<refund> optionalRefund = refundRepository.findById(id);
        if (optionalRefund.isPresent()) {
            refund existingRefund = optionalRefund.get();
            existingRefund.setAmount(newRefund.getAmount());
            existingRefund.setStatus(newRefund.getStatus());
            existingRefund.setRequestDate(newRefund.getRequestDate());
            existingRefund.setProcessedDate(newRefund.getProcessedDate());
            return refundRepository.save(existingRefund);
        } else {
            return null;
        }
    }

    public String deleteRefund(int id) {
        if (refundRepository.findById(id).isPresent()) {
            refundRepository.deleteById(id);
            return "Refund deleted";
        } else {
            return "Refund not deleted";
        }
    }
}
