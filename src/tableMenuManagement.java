import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

public class tableMenuManagement {
    public void tableMenuChoice() {
        CellStyle cell1 = new CellStyle(CellStyle.HorizontalAlign.center);
        CellStyle cell = new CellStyle(CellStyle.HorizontalAlign.left);

        Table t = new Table(1, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        t.addCell("STAFF MANAGEMENT SYSTEM", cell1);
        String []menu = {"[1]. Insert Employee","[2]. Update Employee","[3]. Display Employee","[4]. Remove Employee","[5]. Exit"};
        for(String menus : menu){
            t.addCell(menus,cell);
        }

        t.setColumnWidth(0, 50, 60);

        System.out.println(t.render());
        System.out.print("Choose your option: ");
    }
}
