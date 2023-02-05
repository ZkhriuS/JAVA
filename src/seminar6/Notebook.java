package seminar6;

import java.util.List;
import java.util.Objects;

public class Notebook {
    private String modelName;
    private int ram;
    private int rom;
    private String os;
    private String color;
    private FilterObject filterObject;
    public Notebook(int ram, int rom, String os, String color){
        this.ram = ram;
        this.rom = rom;
        this.os = os;
        this.color = color;
        filterObject = new FilterObject();
    }

    public Notebook(){
        ram = 0;
        rom = 0;
        os = "";
        color = "";
        filterObject = new FilterObject();
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }

    public void setFilterObject(FilterObject filterObject) {
        this.filterObject = filterObject;
    }

    public FilterObject getFilterObject() {
        return filterObject;
    }

    public int getRam() {
        return ram;
    }

    public int getRom() {
        return rom;
    }

    public String getColor() {
        return color;
    }

    public String getModelName() {
        return modelName;
    }

    public String getOs() {
        return os;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\t\t ").append(modelName).append(":\n");
        result.append("\tобъем ОЗУ: ").append(ram).append(";\n");
        result.append("\tобъем ЖД: ").append(rom).append(";\n");
        result.append("\tОС: ").append(os).append(";\n");
        result.append("\tЦвет: ").append(color).append(".\n\n");
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notebook notebook = (Notebook) o;
        boolean result = true;
        switch(notebook.getFilterObject().getCompareBoundary()){
            case '=': {
                if(filterObject.isOsFilter())
                    result = this.os.equals(notebook.os);
                if(filterObject.isColorFilter())
                    result = result && this.color.equals(notebook.color);
                return result;
            }
            case '>': {
                if(filterObject.isRamFilter())
                    result = this.ram >= notebook.ram;
                if(filterObject.isRomFilter())
                    result = result && this.rom >= notebook.rom;
                return result;
            }
            case '<': {
                if(filterObject.isRamFilter())
                    result = this.ram <= notebook.ram;
                if(filterObject.isRomFilter())
                    result = result && this.rom <= notebook.rom;
                return result;
            }
            default:
                return this.modelName.equals(notebook.modelName)
                        && this.ram == notebook.ram && this.rom == notebook.rom
                        && this.os.equals(notebook.os) && this.color.equals(notebook.color);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelName, ram, rom, os, color);
    }
}
