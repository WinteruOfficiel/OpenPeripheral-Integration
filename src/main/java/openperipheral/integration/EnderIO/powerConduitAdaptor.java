package openperipheral.integration.EnderIO;

import crazypants.enderio.conduit.TileConduitBundle;
import crazypants.enderio.conduit.power.IPowerConduit;
import crazypants.enderio.conduit.power.PowerConduit;
import crazypants.enderio.conduit.power.PowerConduitNetwork;
import crazypants.enderio.conduit.power.PowerTracker;
import crazypants.enderio.conduit.power.NetworkPowerManager;
import crazypants.enderio.conduit.IConduitBundle;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.enderio.core.common.util.BlockCoord;

import openperipheral.api.adapter.IPeripheralAdapter;
import openperipheral.api.adapter.method.Arg;
import openperipheral.api.adapter.method.Optionals;
import openperipheral.api.adapter.method.ReturnType;
import openperipheral.api.adapter.method.ScriptCallable;
import scala.tools.nsc.interpreter.Power;

import net.minecraft.world.World;
import net.minecraftforge.*;


public class powerConduitAdaptor implements IPeripheralAdapter {

    @Override
    public Class<?> getTargetClass() {
        return IConduitBundle.class;
    }

    @Override
    public String getSourceId() {
        return "enderIO_powerConduit";
    }

    @ScriptCallable(description = "Get the average input and output from a power conduit.",
            returnTypes = ReturnType.STRING)
    public String getAvgEnergyFlow(IConduitBundle tileEntity) {
     
        if (!(tileEntity instanceof TileConduitBundle)) {
            return "{status: \"error\", message: \"TileEntity is not TileConduitBundle\"}";
        }

        TileConduitBundle bundle = (TileConduitBundle) tileEntity;

        IPowerConduit powa = bundle.getConduit(IPowerConduit.class);

        if (powa == null) {
            System.out.println("IPowerConduit is null");
            return "{status: \"error\", message: \"IPowerConduit is null\"}";
        }

        if (!(powa instanceof PowerConduit)) {
            System.out.println("IPowerConduit is not PowerConduit");
            return "{status: \"error\", message: \"IPowerConduit is not PowerConduit\"}";
        }

        IPowerConduit conduit = powa;

        PowerConduitNetwork pcn = (PowerConduitNetwork) conduit.getNetwork();
        NetworkPowerManager pm = pcn.getPowerManager();
        PowerTracker tracker = pm.getNetworkPowerTracker();

        if (tracker == null) {
            System.out.println("PowerTracker is null");
            return "{status: \"error\", message: \"PowerTracker is null\"}";
        }

        return "{status: \"ok\", avgInput: " + tracker.getAverageRfTickRecieved() + ", avgOutput: " + tracker.getAverageRfTickSent() + ", powerInMachine: " + pm.getPowerInReceptors() + ", maxPowerInMachine: " + pm.getMaxPowerInReceptors() + "}";
    }
}
