package com.example.demo.service;

import com.example.demo.DemoWinterApplication;
import com.example.demo.entity.ArchivoEntity;
import com.example.demo.entity.Resultado;
import com.example.demo.repository.IArchivoCustom;
import com.example.demo.repository.IArchivoRepository;
import com.example.demo.util.FileUtils;
import com.example.demo.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ArchivoServicio implements IArchivoCustom {
    private IArchivoRepository archivoRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public ArchivoServicio(IArchivoRepository archivoRepository) {
        this.archivoRepository = archivoRepository;
    }

    @Override
    public int uploadFile(ArchivoEntity file, MultipartFile[] uploadfiles) {
        int response = ResponseObject.FILE_NOT_UPLOADED_CODE;

        try {
//            if (uploadfiles[0].getOriginalFilename().toLowerCase().endsWith(".jpg")) {
//                file.setPath(DemoApplication.UPLOADED_FOLDER + "/images/" + uploadfiles[0].getOriginalFilename());
//            }
            file.setPath(DemoWinterApplication.UPLOADED_FOLDER + "/" + uploadfiles[0].getOriginalFilename());
            archivoRepository.save(file);
            response = FileUtils.uploadFileOnServer(uploadfiles);

        } catch (Exception e) {
            archivoRepository.delete(file);
        }
        return response;
    }

    @Override
    public List<ArchivoEntity> getAllFiles() {
        return this.archivoRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        File file = new File(this.archivoRepository.findById(id).get().getPath());
        file.delete();
        this.archivoRepository.deleteById(id);
    }

    @Override
    public byte[] openFile(String filename) throws IOException {
        return FileUtils.getFile(filename);
    }

    public List<Resultado> getFun(Integer pId) {
        StoredProcedureQuery query = this.em.createStoredProcedureQuery("organizacion.pa_obtener_usuarios_jerarquia");
        query.registerStoredProcedureParameter("pId", Integer.class, ParameterMode.IN);
        query.setParameter("pId", pId);
        query.execute();

        List<Object> resultList = (List<Object>) query.getResultList();
        List<Resultado> resultados = new ArrayList<>();
        Iterator itr = resultList.iterator();
        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();
            //now you have one array of Object for each row
            Integer client = Integer.parseInt(String.valueOf(obj[0])); // don't know the type of column CLIENT assuming String
            String service = String.valueOf(obj[1]); //SERVICE assumed as int
            //same way for all obj[2], obj[3], obj[4]
            System.out.println(client);
            System.out.println(service);
            Resultado resultado = new Resultado();
            resultado.setPersonaId(Integer.parseInt(String.valueOf(obj[0])));
            resultado.setNombres(String.valueOf(obj[1]));
            resultado.setApellidoPaterno(String.valueOf(obj[2]));
            resultado.setApellidoMaterno(String.valueOf(obj[3]));
            resultado.setOficinaId(Integer.parseInt(String.valueOf(obj[4])));
            resultado.setUsuarioId(Integer.parseInt(String.valueOf(obj[5])));
            resultado.setUsuarioLogin(String.valueOf(obj[6]));
            resultado.setTipoJerarquiaId(Integer.parseInt(String.valueOf(obj[7])));
            resultado.setCargo(String.valueOf(obj[8]));
            resultado.setCorreoCorporativo(String.valueOf(obj[9]));

            resultados.add(resultado);
        }

        return resultados;
    }
}
