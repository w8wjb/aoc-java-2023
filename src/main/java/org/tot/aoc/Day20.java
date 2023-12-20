package org.tot.aoc;

import java.util.*;

public class Day20 {


    private class Module {

        public final String name;

        protected final List<Module> inputs = new ArrayList<>();
        protected final List<Module> ouputs = new ArrayList<>();

        protected final List<String> outputNames;

        protected long lowPulses = 0;
        protected long highPulses = 0;


        public Module(String name, List<String> outputNames) {
            this.name = name;
            this.outputNames = outputNames;
        }


        protected void sendPulse(boolean pulse) {
            for (Module output : ouputs) {
                pulseEvents.add(new PulseEvent(this, output, pulse));
            }
        }

        public void receivePulse(boolean pulse, Module from) {
            if (pulse) {
                highPulses++;
            } else {
                lowPulses++;
            }
        }

        protected void link() {

            for (String n : outputNames) {
                Module output = graph.get(n);
                if (output == null) {
                    throw new IllegalStateException("Missing module " + n);
                }
                this.ouputs.add(output);
                output.inputs.add(this);
            }

        }

        @Override
        public String toString() {
            return name;
        }
    }

    private class Broadcaster extends Module {


        public Broadcaster(String name, List<String> outputs) {
            super(name, outputs);
        }


        @Override
        public void receivePulse(boolean pulse, Module from) {
            sendPulse(pulse);
        }
    }

    private class FlipFlop extends Module {

        private boolean state = false;

        public FlipFlop(String name, List<String> outputs) {
            super(name, outputs);
        }

        @Override
        public void receivePulse(boolean pulse, Module from) {

            if (!pulse) {
                state = !state;
                sendPulse(state);
            }

        }
    }

    private class Conjunction extends Module {

        long period = 0;

        private final Map<String, Boolean> states = new LinkedHashMap<>();

        public Conjunction(String name, List<String> outputs) {
            super(name, outputs);
        }

        @Override
        public void receivePulse(boolean pulse, Module from) {

            if (states.isEmpty()) {
                for (Module input : inputs) {
                    states.put(input.name, false);
                }
            }

            states.put(from.name, pulse);

            period++;

//            if ("gk".equals(name)) {
//
//                String[] registers = new String[]{
//                        "ls", "zg", "hx", "ms", "mj", "sp", "dd", "mz", "xg", "xn", "vm", "rz"
//                };
//
//                String[] registers = new String[]{
//                        "fd", "df", "nv", "tf", "cb", "xc", "vq", "gr", "jh", "kd", "cm", "cf"
//                };
//
//                String[] registers = new String[]{
//                        "kv", "xr", "qq", "dh", "nm", "rb", "xl", "jd", "bm", "fj", "pt", "lg"
//                };
//                String[] registers = new String[]{
//                        "fp", "cc", "tp", "hq", "ql", "bn", "rj", "jk", "dg", "mp", "kj", "fl"
//                };
//
//
//                System.out.print(name);
//                System.out.print(" ");
//                StringBuilder buffer = new StringBuilder(states.size());
//                for (String r : registers) {
//                    boolean value = states.getOrDefault(r, false);
//                    System.out.print(value ? 1 : 0);
//                    buffer.append(value ? '1' : '0');
//                }
//                System.out.print(" ");
//                System.out.println(Long.parseLong(buffer.toString(), 2));
//            }

            for (boolean inputState : states.values()) {
                if (!inputState) {
                    sendPulse(true);
                    return;
                }
            }

            if ("gk".equals(name)) {
                System.out.print(name);
                System.out.print(" ");
                System.out.println(period);
            }
            period = 0;
            sendPulse(false);

        }
    }

    private class PulseEvent {
        public final Module source;
        public final Module destination;
        public final boolean pulse;

        private PulseEvent(Module source, Module destination, boolean pulse) {
            this.source = source;
            this.destination = destination;
            this.pulse = pulse;
        }

        public void send() {
            this.destination.receivePulse(pulse, source);
        }

        @Override
        public String toString() {
            return String.format("%s -%s-> %s", source, pulse ? "high" : "low", destination);
        }
    }

    private final Map<String, Module> graph = new LinkedHashMap<>();

    private final Queue<PulseEvent> pulseEvents = new ArrayDeque<>();

    private void parseGraph(List<String> input) {

        for (String line : input) {
            var parts = line.split("\\s+->\\s+");

            String name = parts[0];
            List<String> outputs = Arrays.asList(parts[1].split(",\\s+"));


            Module module;
            if ("broadcaster".equals(name)) {
                module = new Broadcaster(name, outputs);

            } else if (name.startsWith("%")) {
                module = new FlipFlop(name.substring(1), outputs);
            } else if (name.startsWith("&")) {
                module = new Conjunction(name.substring(1), outputs);
            } else {
                throw new IllegalStateException("Unsupported module type");
            }
            graph.put(module.name, module);
        }

        for (Module module : graph.values()) {
            module.link();
        }

    }

    public long solvePuzzle1(List<String> input) {

        graph.put("output", new Module("output", new ArrayList<>()));
        graph.put("rx", new Module("rx", new ArrayList<>()));

        parseGraph(input);

        Module broadcaster = graph.get("broadcaster");


        long lowPulses = 0;
        long highPulses = 0;

        for (int i = 0; i < 1000; i++) {
            pulseEvents.add(new PulseEvent(null, broadcaster, false));

            while (!pulseEvents.isEmpty()) {
                var event = pulseEvents.poll();
                event.send();
                if (event.pulse) {
                    highPulses++;
                } else {
                    lowPulses++;
                }
            }


        }

        return lowPulses * highPulses;
    }

    public long solvePuzzle2(List<String> input) {

        Module rx = new Module("rx", new ArrayList<>());

        graph.put("output", new Module("output", new ArrayList<>()));
        graph.put(rx.name, rx);

        parseGraph(input);

        Module broadcaster = graph.get("broadcaster");

//        String[] registers = new String[] {
//                "ls", "zg", "hx", "ms", "mj", "sp", "dd", "mz", "xg", "xn", "vm", "rz"
//        };
//
//        String[] registers = new String[] {
//                "fd", "df", "nv", "tf", "cb", "xc", "vq", "gr", "jh", "kd", "cm", "cf"
//        };
//
//        String[] registers = new String[] {
//                "kv", "xr", "qq", "dh", "nm", "rb", "xl", "jd", "bm", "fj", "pt", "lg"
//        };

        long buttonPresses = 0;
        while (true) {
            pulseEvents.add(new PulseEvent(null, broadcaster, false));
            buttonPresses++;

            while (!pulseEvents.isEmpty()) {
                var event = pulseEvents.poll();
                event.send();
            }


//            StringBuilder buffer = new StringBuilder(registers.length);
//            for (String r : registers) {
//                FlipFlop flipFlop = (FlipFlop) graph.get(r);
//                System.out.print(flipFlop.state ? 1 : 0);
//                buffer.append(flipFlop.state ? '1' : '0');
//            }
//            System.out.print(" ");
//            System.out.println(Long.parseLong(buffer.toString(), 2));

            if (rx.lowPulses > 0) {
                return buttonPresses;
            }

            if (buttonPresses == 100000) {
                return 0;
            }
        }

    }

}
