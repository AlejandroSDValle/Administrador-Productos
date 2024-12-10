import { PropsWithChildren } from 'react';

export default function SuccessMessage({ children }: PropsWithChildren) {
  return (
    <div className="text-center my-4 bg-green-100 border border-green-400 text-green-700 rounded-md p-3 flex items-center justify-center gap-2">
      <span>{children}</span>
    </div>
  );
}