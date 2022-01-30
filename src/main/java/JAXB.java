import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.util.List;

public class JAXB {
    JAXBContext context;
    Marshaller marshaller;

    /**
     * Este metodo constructor sirve para crear el XML
     * @param pokemones Recibe una lista con la cual va a trabajar para escribir en el XML
     * @param file Recibe un file el cual  eliminara y despues trabajara con el.
     */
    JAXB(PokemonCollection pokemones, File file) {
        file.delete();
        try {
            context = JAXBContext.newInstance(PokemonCollection.class);

            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(pokemones, file);
            //marshaller.marshal(datas, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
