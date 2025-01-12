package com.scm.scm.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.scm.scm.entity.Contact;
import com.scm.scm.entity.User;
import com.scm.scm.helper.ResourceNotFoundException;
import com.scm.scm.repository.ContactRepository;
import com.scm.scm.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService{
  
    
        @Autowired
        private ContactRepository contactRepo;
    
        @Override
        public Contact save(Contact contact) {
    
            String contactId = UUID.randomUUID().toString();
            contact.setId(contactId);
            return contactRepo.save(contact);
    
        }
    
        @Override
        public Contact update(Contact contact) {
    
            // var contactOld = contactRepo.findById(contact.getId()).orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
            // contactOld.setName(contact.getName());
            // contactOld.setEmail(contact.getEmail());
            // contactOld.setPhone(contact.getPhone());
            // contactOld.setAddress(contact.getAddress());
            // contactOld.setDescription(contact.getDescription());
            // contactOld.setPicture(contact.getPicture());
            // contactOld.setFavority(contact.getFavority());
            // contactOld.setWebsiteLink(contact.getWebsiteLink());
            
            // // contactOld.setCloudinaryImagePublicId(contact.getCloudinaryImagePublicId());
    
            // return contactRepo.save(contactOld);
            return null;
        }
    
        @Override
        public List<Contact> getAll() {
            return contactRepo.findAll();
        }
    
        @Override
        public Contact getById(String id) {
            return contactRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Contact not found with given id " + id));
        }
    
        @Override
        public void delete(String id) {
            var contact = contactRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Contact not found with given id " + id));
            contactRepo.delete(contact);
    
        }
    
        @Override
        public List<Contact> getByUserId(String userId) {
            return contactRepo.findByUserId(userId);
    
        }
    
        @Override
        public Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction) {
    
            // Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
    
            // var pageable = PageRequest.of(page, size, sort);
    
            // return contactRepo.findByUser(user, pageable);
            return null;
    
        }
    
        @Override
        public Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user) {
    
            // Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
            // var pageable = PageRequest.of(page, size, sort);
            // return contactRepo.findByUserAndNameContaining(user, nameKeyword, pageable);
            return null;
        }
    
        @Override
        public Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order,
                User user) {
            // Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
            // var pageable = PageRequest.of(page, size, sort);
            // return contactRepo.findByUserAndEmailContaining(user, emailKeyword, pageable);
            return null;
        }
    
        @Override
        public Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy,
                String order, User user) {
    
            // Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
            // var pageable = PageRequest.of(page, size, sort);
            // return contactRepo.findByUserAndPhoneNumberContaining(user, phoneNumberKeyword, pageable);
            return null;    
        }

        @Override
        public List<Contact> getByUser(User user) {
            return contactRepo.findByUser(user);
        }
    
    }