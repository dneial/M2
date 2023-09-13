package datamining;

import java.util.*;
import representation.*;

public class Apriori extends AbstractItemsetMiner{
    protected BooleanDatabase database;

    public Apriori(BooleanDatabase database){
        super(database);
    }

    public Set<Itemset> frequentSingletons(float frequence){
        Set<Itemset> frequentSingletons = new HashSet<>();
        for(BooleanVariable singleton: this.database.getItems()){
            Set<BooleanVariable> item = new HashSet<>();
            item.add(singleton);
            float freqItem = this.frequency(item);
            if(freqItem >= frequence){
                frequentSingletons.add(new Itemset(item, freqItem));
            }
        }

        return frequentSingletons;
    }

    public static SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable> itemsTries1, SortedSet<BooleanVariable> itemsTries2) {
        SortedSet<BooleanVariable> itemSetCombine = new TreeSet<>();
        if(itemsTries1.size()==0){
            return null;
        }
        if(itemsTries1.size() == itemsTries2.size()) {
            Iterator it1 = itemsTries1.iterator();
            Iterator it2 = itemsTries2.iterator();
            BooleanVariable boolVar1 = (BooleanVariable) it1.next();
            BooleanVariable boolVar2 = (BooleanVariable) it2.next(); 
            while(it1.hasNext()){
                if(boolVar1 != boolVar2){
                    return null;
                }
                boolVar1 = (BooleanVariable) it1.next();
                boolVar2 = (BooleanVariable) it2.next();
                itemSetCombine.add(boolVar1);
            }
            if(boolVar1 == boolVar2){
                return null;
            } else {
                itemSetCombine.add(boolVar1);
                itemSetCombine.add(boolVar2);
            }
            
        } else // if(itemsTries1.size() != itemsTries2.size())
            return null;
        
        return itemSetCombine;
    }

    

    public static boolean allSubsetsFrequent(Set<BooleanVariable> ensembleItems, Collection<SortedSet<BooleanVariable>> collecEnsenbleItems){
        List<BooleanVariable> listeItems = new ArrayList<>(ensembleItems);
        for(int i = 0; i < listeItems.size(); i++){
            List<BooleanVariable> listePartielle = new ArrayList<>(listeItems);
            listePartielle.remove(listePartielle.get(i));
            if(!collecEnsenbleItems.containsAll(listePartielle)) {
                return false;
            }
        } 
        return true;
    }

    @Override
    public Set<Itemset> extract(float freqMin) {
        int nbitems = super.database.getItems().size();
        Set<Itemset> frequentSets = new HashSet<>();
        Set<Itemset> singletons = this.frequentSingletons(freqMin);
        frequentSets.addAll(singletons);
        ArrayList<SortedSet<BooleanVariable>> niveauPrecedent=new ArrayList<>();
        for(Itemset singleton : singletons){
            SortedSet<BooleanVariable> itemTriees = new TreeSet<>(Apriori.COMPARATOR);
            itemTriees.addAll(singleton.getItems());
            niveauPrecedent.add(itemTriees);
        }
        for(int k=2; k<=nbitems;k++){
            ArrayList<SortedSet<BooleanVariable>> niveauCourant = new ArrayList<>();
            for(int i=0; i< niveauPrecedent.size();i++){
                SortedSet<BooleanVariable> items1=niveauPrecedent.get(i);
                for(int j=i+1;j<niveauPrecedent.size();j++){
                    SortedSet<BooleanVariable> items2=niveauPrecedent.get(j);
                    SortedSet<BooleanVariable> items=Apriori.combine(items1,items2);
                    if((items != null) && (Apriori.allSubsetsFrequent(items, niveauPrecedent))){
                        float frequence = super.frequency(items);
                        if(frequence >= freqMin){
                            frequentSets.add(new Itemset(items, frequence));
                            niveauCourant.add(items);
                        }
                    }
                }
            }
            niveauPrecedent = niveauCourant;
        }
        return frequentSets;
    } 
}

