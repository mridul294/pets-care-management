import java.util.List;

 interface PetDAO {
    void addPet(Pet pet);
    Pet getPetById(int id);
    List<Pet> getAllPets();
    void updatePet(Pet pet);
    void deletePet(int id);
}
